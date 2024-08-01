package tests.mail.positive;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

import java.io.File;
import java.io.IOException;

import static constans.Constant.Directory.INPUT_PATH;
import static constans.Constant.Directory.OUTPUT_PATH;
import static constans.Constant.Urls.MAIN_MAIL_PAGE_URL;
import static org.junit.Assert.assertTrue;

public class AttachmentFileInMailEqualsPositiveTest extends BaseTest {

    @Test
    public void AttachmentFileInMailEqualsPositiveTest() {
        String fileName = "TestTextFile.txt";
        File fileSend = new File(INPUT_PATH, fileName);

        basePage.goToUrl(MAIN_MAIL_PAGE_URL);
        signInPages.pasteLogin(user);
        signInPages.clickNext();
        signInPages.pastePassword(user);
        signInPages.clickNext();

        mainMailPage.createNewLetter();

        newLetterPage.checkRecipientMailFieldExist();
        newLetterPage.sendLoginInRecipientMailField(user);
        newLetterPage.clickActionToRecipientOption(action);
        newLetterPage.sendSubjectIntoSubjectBox();
        newLetterPage.addFileToLetter(fileSend.getAbsolutePath());
        newLetterPage.checkThatFileUpload();
        newLetterPage.clickSendBtn();

        mainMailPage.checkThatLetterSend();
        mainMailPage.clickIncomingBtn();
        mainMailPage.sendTextIntoFindField();
        mainMailPage.clickLetterByFoundSubject();

        mailBodyViewPage.moveToAttachField(action);
        mailBodyViewPage.clickDownloadBtn();

        File fileDownload = new File(OUTPUT_PATH, fileName);

        try {
            FileUtils.waitFor(fileDownload, 10);
            Assertions.assertTrue(FileUtils.contentEquals(fileSend, fileDownload), "The files differ");
            FileUtils.delete(fileDownload);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
