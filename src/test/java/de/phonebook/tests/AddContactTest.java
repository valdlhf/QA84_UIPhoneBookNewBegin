package de.phonebook.tests;

import de.phonebook.core.TestBase;
import de.phonebook.model.Contact;
import de.phonebook.utils.MyDataProviders;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class AddContactTest extends TestBase {


    //at first login
    @BeforeMethod
    // и тут мы прописываем прекондишн , что есть мы долны зайти в аккаунт наш
    public void precondition(){

        if(!app.getUser().isLoginLinkPresent()){
            app.getUser().clickOnSignOutButton();
        }

        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new de.phonebook.model.User().setEmail("abcde12345@gmail.com").setPassword("Aa12345!"));
        app.getUser().clickOnLoginButton();
    }
    @Test(dataProvider = "addNewContactFromCsv",dataProviderClass = MyDataProviders.class)
    public void addContactPositiveTest(Contact contact) {

        //click on add button
        app.getContact().clickOnAddLink();
        app.getContact().fillAddContactForm(contact);
        // click on Save button
        app.getContact().clickOnSaveButton();
        //assert
        Assert.assertTrue(app.getContact().verifyByPhone(contact.getPhone()));//alt enter создали метод
    }

    // после себя принято удалять данные чтобы тест был многоразовым поэтому в афтер методе мы удаляем созданный контакт
    @AfterMethod
    public void postCondition(){
        app.getContact().removeContact();
    }



}
