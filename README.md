# InstagramBot
Welcome to my Instagram Bot project. Essentially it was for only personal use, so there is no GUI but rather a script to be ran. 

Motivation: 
I have gotten busier with no little time and energy to be active on social media platforms, so I am making this bot to make me appear active on Instagram.

Tools used: 
I used java paired with the Selenium Web Driver library to create my bot. The Seleneium Web Driver helps locate web elements such as textboxes and buttons and is able to perform human web browsing actions such as typing, clicking, and scrolling.

How the bot watches stories:
The bot locates the first profile icon in the top most section of the feed and clicks it. After a split second of artificial waiting, it locates the next button to skip on to the next story. This process repeats until there is no more next story arrow.

How the bot auto likes your feed's pictures that are posted on current date:
The bot locates the heart icon by its icon size and description of being unliked. It then clicks it and searches for the next heart icon with specific size and unliked properties until there is no more. While it is searching for the unliked heart icons, it also checks the post's date posted description to not include "days" in it. So, any posts after the intial post with "days" in it while obviously be weeks ago due to instagram's feed chronological order display.

How the bot likes and comment on N amount of pictures from X hashtag:
There is a method that takes in parameters of the numbers of posts you want to comment/like and the string for the hashtag you would want to preform this action on. The method takes the hashtag string and adds into a base url of Instagram's explore page. (e.g.: https://www.instagram.com/explore/tags/(taghere)/?hl=en) After reaching the explore page of chosen hashtag, it clicks on the first posts, looks for specific sized heart icon that is unliked and clicks it. It then locates the add comment textbox, clicks on it, and chooses randomly from an array string of responses to input into the reply textbox. Finally, it locates the reply button and clicks it. The bot then looks for the next post arrow and repeats this process N amount of times specified.


Demo: 
https://youtu.be/QxQyZRhagPw (Part I)
https://youtu.be/ylplxg9aTJM (Part II)

