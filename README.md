# AppUtilities  - A collection of some useful utilities in our applications

# Use Case

1. If you want to perform some operations related to dates , strings you can use this library
2. This library has following utils as of now

   ```javascript
   1.BitmapUtils - will have some functionalities or wide range of useful methods related to BITMAP are there in this
   2.CommonUtils - will have most common useful methods covered in this
   3.DateTimeUtils - is the class which contains methods related to Date operations
   4.PopupUtils - contains different types of message showing options to the users
   5.FragmentUtils - contains fragment related functionalities and methods
   6.ServiceAndJobSchedulerUtils - related to service start ,stop running status and job scheduler 
   7.StringUtils - will have methods related to string & it's opertaions
   8.ValidationUtils - some useful validations will be there in this
   ```
# How it works?

In our Activity/Fragment we can use any of the above classes.

For example

# BitmapUtils

Here this is used to convert drawable into Bitmap.

```kotlin
   BitmapUtils.drawableToBitmap(resources.getDrawable(R.mipmap.ic_launcher))
```

You can find number of useful methods related to bitmap in `BitmapUtils` class.

# CommonUtils

Here this is used to navigate user to playstore to check whether we have any update or not.

```kotlin
   CommonUtils.navigateToPlayStore(this)
```

You can find number of useful methods in `CommonUtils` class.

# DateTimeUtils

Here this is used to get current date time in required format. 
```kotlin
   DateTimeUtils.getCurrentDateTime("dd-mm-yyyy")
```

You can find number of useful methods related to date and time in `DateTimeUtils` class.


# PopupUtils

Here this is used to show message to user.
```kotlin
   PopupUtils.showMessage(this,"App updates are available!")
```

You can find number of useful methods in `PopupUtils` class.

# ServiceAndJobSchedulerUtils

Here this is used to check whether my service is running or not.
```kotlin
   ServiceAndJobSchedulerUtils.isMyServiceRunning(this,MyService::class.java)
```

You can find other useful methods in `ServiceAndJobSchedulerUtils` class.

# StringUtils

Here this is used to check whether the passed string is empty or non-empty or null.

```kotlin
   StringUtils.isEmpty("Hello")
```

You can find other useful methods in `StringUtils` class.

# ValidationUtils

Here this is used to check whether the phone number is valid or not.

```kotlin
   ValidationUtils.isValidPhoneNumber("2134")
```

You can find other useful methods in `ValidationUtils` class.

