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
        if(author!=null)
         this.Author=author;
        else
            this.Author = "111";
    }
    public void SetCategory(String category)
    {
        if(category!=null)
        this.Category=category;
        else
            this.Category = "111";
    }
    public void SetLink(String link)
    {
        if(link!=null)
        this.Link=link;
        else
            this.Link="111";
    }
    public void SetDescription(String description)
    {
        if(description!=null)
        this.Description=description;
        else
            this.Description="111";
    }
    public void SetPubDate(String pubDate)
    {
        if(pubDate!=null)
        this.PubDate=pubDate;
        else
            this.PubDate="111";
    }

    public String getTitle()
    {
       return Title;
    }
    public String getAuthor() {return Author;}
    public String getCategory() {return Category;}
    public String getLink() {return Link;}
    public String getDescription() {return Description;}
    public String getPubDate() {return PubDate;}



}
