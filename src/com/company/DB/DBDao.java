package com.company.DB;

import java.util.List;

public interface DBDao {


    List<?> get();

    default void update() {

    }

    default void delete() {

    }

}
