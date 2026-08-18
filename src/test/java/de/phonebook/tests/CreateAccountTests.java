package de.phonebook.tests;

import de.phonebook.core.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {


    @BeforeMethod
    public void ensurePrecondition(){
        if(!app.getUser().isLoginLinkPresent()){
            app.getUser().clickOnSignOutButton();
        }
    }


    @Test(enabled = false) //я не поняла зачем мы это написали и поэтому закомментировываю перед прогоном теста
    public void newUserRegisterPositiveTest(){
        // и тут прописываем все для переменной i
        int i = (int) ((System.currentTimeMillis()/1000)%3600);//alt enter->cast expression to int
        //копируем это в и делаем из этого метод  и здесь убрали (закомментировали)

        //click on Login link
        app.getUser().clickOnLoginLink();

        //enters email
        //type(By.name("email"), "abcde12345@gmail.com"); //выделили 3 строки extract -> method -> переименовали в type -> шестеренка -> more options
        // -> сделала его паблик -> refactor -> keep original signature -> all

        // чтобы он каждый раз создавал новый имейл мы добавляем переменную i которая будет каждый раз меняться
        // после того как создали метод то вставляем сюда его
        app.getUser().fillLoginRegisterForm(new de.phonebook.model.User().setEmail("abcde12345@gmail.com").setPassword("Aa12345!"));

        //click on Registration button
        app.getUser().clickOnRegistrationButton();

        //assert SingOut button
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());

    }

    @Test
    public void newUserRegisterNegativeTest(){
        //click on Login link
        app.getUser().clickOnLoginLink();

        app.getUser().fillLoginRegisterForm(new de.phonebook.model.User().setEmail("abcde12345@gmail.com").setPassword("Aa12345!"));

        //click on Registration button
        app.getUser().clickOnRegistrationButton();

        //assert SingOut button
        Assert.assertTrue(app.getUser().isAlertPresent());
    }



}
