package aisa.application.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alier on 7/22/2017.
 */

public class SimplifiedCourses extends RealmObject {
    @PrimaryKey
    private String name;
    private RealmList<SimplifiedCourse> courses;

    public SimplifiedCourses(){}

    public SimplifiedCourses(String name, RealmList<SimplifiedCourse> list){
        this.name = name;
        this.courses = list;
    }

    public String getName() {
        return name;
    }

    public RealmList<SimplifiedCourse> getCourses() {
        return courses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourses(RealmList<SimplifiedCourse> courses) {
        this.courses = courses;
    }
}
