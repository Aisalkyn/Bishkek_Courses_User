package aisa.application.models;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.realm.RealmList;
import io.realm.RealmObject;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ForumService {
    Gson gson = new GsonBuilder()
            .setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return f.getDeclaringClass().equals(RealmObject.class);
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            })
            .create();
    Retrofit builder = new Retrofit.Builder()
            .baseUrl("http://45.55.194.219:3000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    @GET("getAllCategories")
    Call<RealmList<Category>> getAllCategories();

    @GET("getCoursesBySubcategory/{subCategoryname}")
    Call<RealmList<SimplifiedCourse>> getSimplifiedCourses(@Path("subCategoryname") String subC);

    @GET("getCourseByName/{coursename}")
    Call<Course> getCourse(@Path("coursename") String courseName);
}

