package com.company.DB;

import java.util.List;

public interface DB {


    List<?> get();

    default void update() {

    }

    default void delete() {

    }

}
