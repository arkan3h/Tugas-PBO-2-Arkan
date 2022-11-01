class SocialGraph{
    public static void compareLike(Likeable social1, Likeable social2){
        if(social1.totalLike()>social2.totalLike()){
            System.out.println(social1.platform()+" "+social1.user()+" lebih populer dari "+social2.platform()+" "+social2.user());
        }
        else if(social1.totalLike()<social2.totalLike()){
            System.out.println(social2.platform()+" "+social2.user()+" lebih populer dari "+social1.platform()+" "+social1.user());
        }
        else{
            System.out.println("Kedua user sama-sama populer");
        }
    }
}

interface Likeable{
    public String platform();
    public String user();
    public int totalLike();
    public int like();
}

class Facebook implements Likeable{
    private String status;
    private String user;
    private int like = 0;
    public Facebook(String user, String status){
        this.user = user;
        this.status = status;
    }
    public String status(){
        return this.status;
    }
    public String user(){
        return this.user;
    }
    public int like(){
        this.like++;
        return this.like;
    }
    public int totalLike(){
        return this.like;
    }
    public String platform() {
        return "Facebook";
    }
}

class Twitter implements Likeable{
    private String tweet;
    private String user;
    private int favorite;
    public Twitter(String user, String tweet){
        this.user = user;
        this.tweet = tweet;
    }
    public String tweet(){
        return this.tweet;
    }
    public String user(){
        return this.user;
    }
    public int favorite(){
        this.favorite++;
        return this.favorite;
    }
    public int totalFavorite(){
        return this.favorite;
    }
    public int like(){
        this.favorite();
        return this.favorite;
    }
    public int totalLike(){
        return this.favorite;
    }
    public String platform() {
        return "Tritter";
    }
}

public class Main {
    public static void main(String[] args) {
        Facebook fbTukul = new Facebook("Tukul Arwana", "Kembali ke laptop!");
        fbTukul.like();
        fbTukul.like();

        Twitter twJokowi = new Twitter("Joko Widodo", "Aku rapopo..");
        twJokowi.favorite();
        twJokowi.favorite();
        twJokowi.favorite();

        SocialGraph socialGraph = new SocialGraph();
        socialGraph.compareLike(fbTukul, twJokowi);
    }
}
