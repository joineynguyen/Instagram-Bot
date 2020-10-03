# InstagramBot
Welcome to my Instagram Bot project. Essentially it was for only personal use, so there is no GUI but rather a script to be ran. 

**Motivation:**
I have gotten busier with no little time and energy to be active on social media platforms, so I am making this bot to make me appear active on Instagram.

Tools used: 
I used java paired with the Selenium Web Driver library to create my bot. The Seleneium Web Driver helps locate web elements such as textboxes and buttons and is able to perform human web browsing actions such as typing, clicking, and scrolling.

How the bot watches stories:
The bot locates the first profile icon in the top most section of the feed and clicks it. After a split second of artificial waiting, it locates the next button to skip on to the next story. This process repeats until there is no more next story arrow.

How the bot auto likes your feed's pictures that are posted on current date:
The bot locates the heart icon by its icon size and description of being unliked. It then clicks it and searches for the next heart icon with specific size and unliked properties until there is no more. While it is searching for the unliked heart icons, it also checks the post's date posted description to not include "days" in it. So, any posts after the intial post with "days" in it while obviously be weeks ago due to instagram's feed chronological order display.

How the bot likes and comment on N amount of pictures from X hashtag:
There is a method that takes in parameters of the numbers of posts you want to comment/like and the string for the hashtag you would want to preform this action on. The method takes the hashtag string and adds into a base url of Instagram's explore page. (e.g.: https://www.instagram.com/explore/tags/(taghere)/?hl=en) After reaching the explore page of chosen hashtag, it clicks on the first posts, looks for specific sized heart icon that is unliked and clicks it. It then locates the add comment textbox, clicks on it, and chooses randomly from an array string of responses to input into the reply textbox. Finally, it locates the reply button and clicks it. The bot then looks for the next post arrow and repeats this process N amount of times specified.

What I've Learned: After completing this small script project, I gained experience in a new library called Selenium Web Driver. I learned that this library can replicate human web browsing almost completely. I understand why CAPTCHA's are mandatory for certain websites to prevent from being abused with this tool. I learned more about the structure and concept about CSS while inspecting websites and its structure in order to use the library methods, which need parameters of CSS ID's.(locating strategies by name, class, ID, tag, single attribute, multiple attribute) I also learned about explicit and implicit waits. They are basically like java sleep method but are used to let the web pages load before running an action.

Problems I've Faced: Since Instagram has a big user base, there are naturally more users to abuse instagram. Instagram was smart to know that instagram bot creators want to complete one action, to create a bot that auto likes pictures. This is the prime action any instagram bot should do. To counter this, instagram developers changed the CSS ID name of the heart randomly after each page refresh and for each post, so implementing an auto liking feature would be harder. I found a way to counter this by having the bot search the like button by its characteristics and/or navigating to it by its neighboring web elements such as the timedate of the post.


Demo: 
https://youtu.be/QxQyZRhagPw (Part I)
https://youtu.be/ylplxg9aTJM (Part II)
