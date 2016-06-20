/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kingmed.dp.gateway.dto;

import java.io.Serializable;

/**
 *
 * @author zhengjunjie
 */

public class SlideDto implements Serializable{
    private String barcode;
    private String label;
    private String overview;
    private String labelWithOverview;
    private String url;
    private String scannerModel;
    private String createDateTime;
    private String scannerCode;
}
