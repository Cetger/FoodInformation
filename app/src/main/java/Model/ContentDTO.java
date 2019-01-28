package Model;

import java.util.List;

public class ContentDTO  extends BaseDTO
{
    private String Ingredients ;
    private NutritionFacts NutritionFact ;
    private ProductDTO Product ;
    private String Warnings ;
    private String CookingTips ;
    private String Recommendations ;
    private String VideoURL ;
    private LanguagesClass Language ;
    private String Details ;
    private CommentDTO [] Comments;
    private String AverageVote ;
    private ContentDTO Result;

    public ContentDTO(String ingredients, NutritionFacts nutritionFact, ProductDTO product, String warnings, String cookingTips, String recommendations, String videoURL, LanguagesClass language, String details) {
        Ingredients = ingredients;
        NutritionFact = nutritionFact;
        Product = product;
        Warnings = warnings;
        CookingTips = cookingTips;
        Recommendations = recommendations;
        VideoURL = videoURL;
        Language = language;
        Details = details;
    }

    public ContentDTO getResult() {
        return Result;
    }

    public void setResult(ContentDTO result) {
        Result = result;
    }

    public ContentDTO(String ingredients, String recommendations, NutritionFacts nutritionFact) {
        Ingredients = ingredients;
        Recommendations = recommendations;
        this.NutritionFact = nutritionFact;
    }

    public String getIngredients() {
        return Ingredients;
    }

    public void setIngredients(String ingredients) {
        Ingredients = ingredients;
    }

    public NutritionFacts getNutritionFact() {
        return NutritionFact;
    }

    public void setNutritionFact(NutritionFacts nutritionFact) {
        NutritionFact = nutritionFact;
    }

    public ProductDTO getProduct() {
        return Product;
    }

    public void setProduct(ProductDTO product) {
        Product = product;
    }

    public String getWarnings() {
        return Warnings;
    }

    public void setWarnings(String warnings) {
        Warnings = warnings;
    }

    public String getCookingTips() {
        return CookingTips;
    }

    public void setCookingTips(String cookingTips) {
        CookingTips = cookingTips;
    }

    public String getRecommendations() {
        return Recommendations;
    }

    public void setRecommendations(String recommendations) {
        Recommendations = recommendations;
    }

    public String getVideoURL() {
        return VideoURL;
    }

    public void setVideoURL(String videoURL) {
        VideoURL = videoURL;
    }

    public LanguagesClass getLanguage() {
        return Language;
    }

    public void setLanguage(LanguagesClass language) {
        Language = language;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public CommentDTO[] getComments() {
        return Comments;
    }

    public void setComments(CommentDTO[] comments) {
        Comments = comments;
    }

    public String getAverageVote() {
        return AverageVote;
    }

    public void setAverageVote(String averageVote) {
        AverageVote = averageVote;
    }
}
