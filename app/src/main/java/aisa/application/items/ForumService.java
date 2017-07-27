package aisa.application.items;

import java.util.List;

import aisa.application.model.Categories;
import aisa.application.model.ContactType;
import aisa.application.model.Courses;
import aisa.application.model.SimplifiedCourse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by admin on 7/11/17.
 */

public interface ForumService {

    Retrofit builder = new Retrofit.Builder()
            .baseUrl("http://45.55.194.219:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @GET("getAllContactTypes")
    Call<List<ContactType>> getALLContactTypes();

    @GET("getAllCategories")
    Call<List<Categories>> getAllCategories();

    @GET("getAllCourses")
    Call<List<Courses>> getAllCourses();

    @GET("getAllSimplifiedCourses")
    Call<List<SimplifiedCourse>> getAllSimplifiedCourses();

    @POST("addCourse")
    Call<Courses> createCourse(@Body Courses courses);

    @POST("addCategory")
    Call<Categories> createCategory(@Body Categories categories);

    @POST("addSubCategories")
    Call<Categories> createSubcategory(@Body Categories postSubCategories);

    @DELETE("deleteCourse/{coursename}")
    Call<ResponseBody> deleteCourse(@Path("coursename") String courseName);

    @DELETE("deleteCategory/{categoryname}")
    Call<ResponseBody> deleteCategory(@Path("categoryname") String categoryName);

    @DELETE("deleteSubCategory/{subcategoryname}")
    Call<ResponseBody> deleteSubcategory(@Path("subcategoryname") String subcategoryName);
}
