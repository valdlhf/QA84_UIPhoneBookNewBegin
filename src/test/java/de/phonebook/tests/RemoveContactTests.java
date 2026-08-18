package de.phonebook.tests;

import de.phonebook.core.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase {

    //before - login, add contact
    @BeforeMethod
    public void precondition(){

        if(!app.getUser().isLoginLinkPresent()){
            app.getUser().clickOnSignOutButton();
        }

        //login
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new de.phonebook.model.User().setEmail("abcde12345@gmail.com").setPassword("Aa12345!"));
        app.getUser().clickOnLoginButton();
        //add contact
        app.getContact().clickOnAddLink();
        app.getContact().fillAddContactForm(new de.phonebook.model.Contact().setName("Tom").setLastName("Li").setPhone("1234567890").setEmail("tomli@gmail.com").setAddress("Berlin").setDescription("Bro"));
        app.getContact().clickOnSaveButton();
    }

    @Test
    public void removeContactTest(){
        int sizeBefore = app.getContact().sizeOfContacts();//alt enter создали этот метод
        //click on card
        app.getContact().removeContact();
        //пауза
        app.getContact().pause(1000);
        int sizeAfter = app.getContact().sizeOfContacts();
        //assert by size
        Assert.assertEquals(sizeAfter,sizeBefore-1);

    }

}
