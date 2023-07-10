package com.utility.webDrivers;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

    public static RemoteWebDriver createInstance(ExtentTest logTest, String browser){
        //Sẽ thêm điều kiện để chọn loại Driver tương ứngkhi khởi tạo
        // nếu có xử lí nhiều kiểu Driver (Ví dụ: GridDriver,...)
        LocalDriver driver = new LocalDriver();

        try {
            return driver.initializeDriver(logTest, browser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
