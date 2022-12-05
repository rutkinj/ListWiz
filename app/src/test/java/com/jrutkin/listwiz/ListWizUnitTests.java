package com.jrutkin.listwiz;

import static org.junit.Assert.assertEquals;

import com.jrutkin.listwiz.models.TaskModel;

import org.junit.Test;

public class ListWizUnitTests {

    @Test public void createTask(){
        TaskModel sut = new TaskModel("Change Hairdo", "Beehive");
        assertEquals("Change Hairdo", sut.getTaskName());
    }
}
