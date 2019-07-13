package com.example.entregable3.controller;

import com.example.entregable3.model.dao.ObraDAO;
import com.example.entregable3.model.pojo.ObraContainer;
import com.example.entregable3.retrofit.ResultListener;
import com.example.entregable3.util.FoundListener;

public class ObraController {

    public static void getObras(final FoundListener<ObraContainer> listener)
    {
        ObraDAO dao = new ObraDAO();
        dao.getObras(listener);
    }

}
