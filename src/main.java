package newpackage;

import org.openqa.selenium.By; // to find elements by classname, xpath, etc
import org.openqa.selenium.WebDriver; //to automate browsing 
import org.openqa.selenium.WebElement; //to store web elements
import org.openqa.selenium.chrome.ChromeDriver; //to automate browsing specifically for chrome browser

import org.openqa.selenium.support.ui.ExpectedConditions; //to help wait for a execution to be done
import org.openqa.selenium.support.ui.WebDriverWait; //to help wait for a execution to be done

import java.util.Random; //used to randomize number for random comment phrase in string array


public class InstagramBot 
{
	String username;
	String password;
	String baseUrl = "http://www.instagram.com";
	WebDriver driver = new ChromeDriver();
	//gives 10 second for driver to find an element or else returns null
	WebDriverWait wait = new WebDriverWait(driver, 3); 
	
	
	public InstagramBot(String username, String password)
	{
		this.username = username;
		this.password = password;
		
		System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver.exe");
		
		login();
	}
	
	void login()
	{
		//initiate web driver
		
		driver.get(baseUrl);
		
		driver.manage().window().maximize();
		
		//handle login username insertion
		WebElement usernameElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"loginForm\"]/div/div[1]/div/label/input")));
		usernameElement.click();
		usernameElement.sendKeys(this.username);
		
		//handle login password insertion
		WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"loginForm\"]/div/div[2]/div/label/input")));
		passwordElement.click();
		passwordElement.sendKeys(this.password);
		
		//click login
		WebElement loginElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"loginForm\"]/div/div[3]/button/div")));
		loginElement.click();
		
		//skip "save login info" questions
		WebElement notNowElement1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"react-root\"]/section/main/div/div/div/div/button")));
		notNowElement1.click();
		WebElement notNowElement2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div/div/div/div[3]/button[2]")));
		notNowElement2.click();
		
	}
	
	//after finishing any bot automation function, we want to always return to the top of the page so next automation function can perform correctly
	void goTopOfPage()
	{
		WebElement instagramLogo = driver.findElement(By.className("s4Iyt"));
		instagramLogo.click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
		
	void likeAllPostsOnFeed()
	{
		//to counter Instagram's like limit of 700 post likes a day
		final int likeLimit = 700;
		int likeCounter = 0;
		
		//find post that is unliked
		WebElement currentLikeButton = driver.findElement(By.xpath("//*[@aria-label='Like' and @height='24']"));
		
		//while there are more posts that is unliked and we are under our like limit for the day
		while(currentLikeButton != null && likeCounter < likeLimit)
		{
			currentLikeButton.click();
			
			//delay every like by 1 second to counter bot activity suspicion
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			//try to find next available post to like
			currentLikeButton = driver.findElement(By.xpath("//*[@aria-label='Like' and @height='24']"));
			
			likeCounter += 1;
		}
		
		//to get ready for other automation functions to work as they work best at the top of the page
		goTopOfPage();
	}
	
	void likePostsOnFeedFromToday()
	{
		//to counter Instagram's like limit of 700 post likes a day
		final int likeLimit = 700;
		int likeCounter = 0;
		
		//find post that is unliked
		WebElement currentLikeButton = driver.findElement(By.xpath("//*[@aria-label='Like' and @height='24']"));
		
		//while there are more posts that is unliked and we are under our like limit for the day
		while(currentLikeButton != null && likeCounter < likeLimit)
		{
			//traverse up the like button's tree until we have the parent that also contains the post's datetime 
			WebElement parent = currentLikeButton.findElement(By.xpath("./.."));
			parent = parent.findElement(By.xpath("./.."));
			parent = parent.findElement(By.xpath("./.."));
			parent = parent.findElement(By.xpath("./.."));
			parent = parent.findElement(By.xpath("./.."));
			parent = parent.findElement(By.xpath("./.."));
			
			//get time web element of current post by class name
			WebElement timeElement = parent.findElement(By.className("c-Yi7"));  
			
			//get text from time web element and stop liking posts if we run into a post that is at least 1 day old
			String timePosted = timeElement.getText();
			if(timePosted.contains("DAY"))
			{
				break;
			}
			
			currentLikeButton.click();
			
			//delay every like by 1 second to counter bot activity suspicion
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			//try to find next available post to like
			currentLikeButton = driver.findElement(By.xpath("//*[@aria-label='Like' and @height='24']"));
			
			likeCounter += 1;
		}
		
		//to get ready for other automation functions to work as they work best at the top of the page
		goTopOfPage();
	}

	void watchStories()
	{
		
		//web element for first story
		WebElement storyElement = wait.until(ExpectedConditions.elementToBeClickable(By.className("OE3OK")));
		storyElement.click();
		
		//waits 100 milliseconds to let story load
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	
		
		while(true)
		{
			//web element for next story button	
			try
			{
				WebElement nextStory = wait.until(ExpectedConditions.elementToBeClickable(By.className("coreSpriteRightChevron")));
				nextStory.click();
			}
			catch(Exception e)
			{
				break;
			}
			
		
			//waits 100 milliseconds to let next button load
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		
		}
		
		//to get ready for other automation functions to work as they work best at the top of the page
		goTopOfPage();
	}
	
	void likePostsByHashtag(String hashtag, int numOfPostsToLike)
	{
		//string array of pre-defined phrases for commenting
		String[] phrases = {"Wow!", "Amazing :)", "Great pic!", "Sheeesh! :o", "Looks wonderful! :D", "Stunning!", "Very nice!", "Fantastic!", "Love this!"};
		//used to randomize the comment phrases
		Random rand = new Random();
		
		String baseExplorePageUrl = "https://www.instagram.com/explore/tags/";
		driver.get(baseExplorePageUrl + hashtag);
		WebElement firstPic = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"react-root\"]/section/main/article/div[1]/div/div/div[1]/div[1]/a/div")));
		firstPic.click();
		
		//lets picture load
		try 
		{
			Thread.sleep(1000);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		int likeCounter = 0;
		
		while(likeCounter < numOfPostsToLike)
		{
			//see if current post is unliked
			boolean postUnliked = driver.findElements(By.xpath("//*[@aria-label='Like' and @height='24']")).size() > 0;  
			
			//if unliked, then like and comment on the post
			if(postUnliked)
			{
				WebElement likeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@aria-label='Like' and @height='24']")));
				likeButton.click();
				WebElement commentTextbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@aria-label='Add a comment…']")));
				commentTextbox.click();
				commentTextbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@aria-label='Add a comment…']")));
				commentTextbox.click();
				
				String phrase = phrases[rand.nextInt(9)];
				commentTextbox.sendKeys(phrase);
				WebElement postButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@type='submit']")));
				postButton.click();
				
				//waits 2 seconds to let the comment get posted
				try
				{
					Thread.sleep(2000);
				}
				catch(Exception e)
				{
					
				}
				
				likeCounter += 1;
			}
			
			//clicks next arrow 
			WebElement nextButton = driver.findElement(By.xpath("//*[@class=' _65Bje  coreSpriteRightPaginationArrow']"));
			nextButton.click();
			
			//lets next post load
			try
			{
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
				
			}
		}
		
	}
	
	public static void main(String[] args)
	{
	
		InstagramBot myBot = new InstagramBot("username", "password");
		//myBot.watchStories();
		//myBot.likePostsOnFeedFromToday();
		myBot.likePostsByHashtag("hashtag", 10);
		
	}
}

