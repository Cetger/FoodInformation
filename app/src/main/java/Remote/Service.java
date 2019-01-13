package Remote;

import Model.BarcodeDTO;
import Model.CategoryClass;
import Model.CategoryLanguage;
import Model.CategoryNameDTO;
import Model.ContentDTO;
import Model.CreateUserClass;
import Model.ErrorClass;
import Model.LanguageAndProductDTO;
import Model.LanguagesClass;
import Model.LoginClass;
import Model.Parameter;
import Model.PostResponse;
import Model.ProductDTO;
import Model.ProductDTO2;
import Model.UpdateCategoryNameDTO;
import Model.UserDTO;
import Model.Values;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Service
{
  //******LoginController***********

  @POST("/api/Login/CheckUserOnLogin/")
  Call<LoginClass> Login(@Body LoginClass value);

  //******ProductController***********

  @POST("/api/Product/CreateProduct/")
Call<ProductDTO> CreateProduct(@Body ProductDTO value);
  @POST("/api/Product/GetProductNameByBarcodeId/")
  Call<ProductDTO> GetProductNameByBarcodeId(@Body BarcodeDTO value);



  //******UserController***********

  @POST("/api/User/CreateUser/")
  Call<CreateUserClass> CreateUser(@Body CreateUserClass value);
  @POST("/api/User/GetUserDetailByUsername/")
  Call<UserDTO> GetUserDetailByUsername(@Body UserDTO value);
  @POST("/api/User/SetModeratorByUsername/")
  Call<UserDTO> SetModeratorByUsername(@Body UserDTO value);
  @POST("/api/User/SetAdminByUsername/")
  Call<UserDTO> SetAdminByUsername(@Body UserDTO value);
  @POST("/api/User/GetFirstUser/")
  Call<UserDTO> GetFirstUser();

  //******ErrorController***********

  @POST("/api/Language/GetLanguageList/")
  Call<LanguagesClass> Languages();
  @POST("/api/Language/GetLanguageListOfProductByBarcodeId/")
  Call<LanguagesClass> GetLanguageListOfProductByBarcodeId(@Body BarcodeDTO barcodeDTO);

    //******ErrorController***********

  @POST("/api/Error/GetErrorList/")
  Call<ErrorClass> Errors();

    //******ContentController***********

  @POST("/api/Content/GetProductContentByLanguageCode/")
  Call<ContentDTO> GetProductContentByLanguageCode(@Body LanguageAndProductDTO contentDTO);

  @POST("/api/Content/CreateContentOfProduct/")
  Call<ContentDTO> CreateContentOfProduct(@Body ContentDTO contentDTO);


   //******CategoryController***********

  @POST("/api/Category/GetProductCategoriesByLanguageCode/")
  Call<CategoryClass> Categories(@Body CategoryLanguage categoryLanguage);
  @POST("/api/Category/AddCategory/")
  Call<CategoryNameDTO> AddCategory(@Body CategoryNameDTO categoryLanguage);
  @POST("/api/Category/DeleteCategoryByLanguageCode/")
  Call<CategoryNameDTO> DeleteCategoryByLanguageCode(@Body CategoryNameDTO categoryLanguage);
  @POST("/api/Category/UpdateCategoryName/")
  Call<UpdateCategoryNameDTO> UpdateCategoryName(@Body UpdateCategoryNameDTO categoryLanguage);
}
