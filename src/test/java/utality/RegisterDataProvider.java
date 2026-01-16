package utality;

import org.testng.annotations.DataProvider;

public class RegisterDataProvider {

    @DataProvider(name = "validRegistrationData")
    public static Object[][] validRegistrationData() {
        return new Object[][]{
            {
            	"pratik",
                "jawale",
                "test@gmail.com",
                "7666907396",
                "Test@1234"
            }
        };
    }

    @DataProvider(name = "invalidRegistrationData")
    public static Object[][] invalidRegistrationData() {
        return new Object[][]{
            {
                "manas",
                "kumbhar",
                "manas@gmail.com",
                "9" + (int)(Math.random() * 1000000000),  
                "Test@756"
            },
            {
                "ram",
                "patil",
                "ram@gmail.com",
                "9" + (int)(Math.random() * 1000000000),  
                "ram@1234"
            }
        };
    }
}

