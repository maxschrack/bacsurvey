package bac.fixture;

import bac.model.MetaPage;
import bac.model.Questionnaire;
import bac.model.User;
import bac.model.enums.EStatus;

import java.util.ArrayList;

/**
 * Created by max on 16/02/16.
 */
public class TestData {

    public static User getUser_Creator(){
        User user = new User();
        user.setId(1L);
        user.setEmail("max@schrack.info");
        user.setPassword("Wert1234");
        user.setDeleted(false);

        return user;
    }

    public static Questionnaire getQuestionnaire(){
        Questionnaire questionnaire = new Questionnaire();

        questionnaire.setId(1L);
        questionnaire.setName("Test Questionnaire");
        questionnaire.setStatus(EStatus.draft);
        questionnaire.setProgressbar(true);
        questionnaire.setStartPage(getMetaPage_Start());
        questionnaire.setEndPage(getMetaPage_End());
        questionnaire.setUser(getUser_Creator());
        questionnaire.setPages(new ArrayList<>());
        questionnaire.setDeleted(false);

        return questionnaire;
    }

    public static MetaPage getMetaPage_Start(){
        MetaPage metaPage = new MetaPage();
        metaPage.setId(1L);
        metaPage.setTitle("Start Page");
        metaPage.setText("Test Text on Start Page");
        metaPage.setDeleted(false);
        //metaPage.setQuestionnaire(getQuestionnaire());

        return metaPage;
    }

    public static MetaPage getMetaPage_End(){
        MetaPage metaPage = new MetaPage();
        metaPage.setId(2L);
        metaPage.setTitle("End Page");
        metaPage.setText("Test Text on End Page");
        metaPage.setDeleted(false);
        //metaPage.setQuestionnaire(getQuestionnaire());

        return metaPage;
    }


}
