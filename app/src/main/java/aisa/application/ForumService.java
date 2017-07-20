package aisa.application;

import java.util.List;

import aisa.application.models.Categories;
import aisa.application.models.Course;
import aisa.application.models.SimplifiedCourse;
import aisa.application.models.SubCategories;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by admin on 7/18/17.
 */

public interface ForumService {

    Retrofit builder = new Retrofit.Builder()
            .baseUrl("http://45.55.194.219:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("getAllCategories")
    Call<List<Categories>> getAllCategories();

    @GET("/getCoursesBySubcategory/{subCategoryname}")
    Call<List<SimplifiedCourse>> getCoursesBySubcategory(@Path("subCategoryname") String sub);

    @GET("/getAllSimplifiedCourses")
    Call<List<SimplifiedCourse>> getAllSimplifiedCourses();
}

