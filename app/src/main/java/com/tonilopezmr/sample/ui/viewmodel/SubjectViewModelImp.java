package com.tonilopezmr.sample.ui.viewmodel;

import com.tonilopezmr.sample.domain.Subject;

/**
 * Created by toni on 05/02/15.
 */
public class SubjectViewModelImp implements SubjectViewModel {

    private int id;
    private String name;

    public SubjectViewModelImp(String name) {
        this.name = name;
    }

    public SubjectViewModelImp(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof SubjectViewModel){
            SubjectViewModel model = (SubjectViewModel)o;
            if (this.id == model.getId() && this.name.equals(model.getName())){
                result = true;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return id+" "+name;
    }
}
