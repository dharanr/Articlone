package dharanyuvi.android.com.articles.models;

public class TheHinduArticle {
    private String Title;
    private String Author;
    private String Category;
    private String Link;
    private String Description;
    private String PubDate;

    public TheHinduArticle()
    {
        this.Title = "";
        this.Author = "";
        this.Category = "";
        this.Link = "";
        this.Description="";
        this.PubDate="";
    }

    //Getters
    public TheHinduArticle(String title,String Author,String Category,String Link,String Description,String pubdate)
    {
        this.Title = title;
        this.Author = Author;
        this.Category =Category;
        this.Link = Link;
        this.Description = Description;
        this.PubDate = pubdate;
    }

    //Setters
    public void SetTitle(String title)
    {
         this.Title=title;

    }
    public void SetAuthor(String author)
    {
         this.Author=author;
    }
    public void SetCategory(String category)
    {
        this.Category=category;
    }
    public void SetLink(String link)
    {
        this.Link=link;
    }
    public void SetDescription(String description)
    {
        this.Description=description;
    }
    public void SetPubDate(String pubDate)
    {
        this.PubDate=pubDate;
    }

    public String getTitle()
    {
       return Title;
    }
}
