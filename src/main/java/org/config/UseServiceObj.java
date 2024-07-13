package org.config;

import org.service.dbImpl.ItemImplDb;
import org.service.dbImpl.ProductImplDb;
import org.service.dbImpl.SaleImplDb;
import org.service.impl.ItemImpl;
import org.service.impl.ProductImpl;
import org.service.impl.SaleImpl;
import org.service.inter.ItemInter;
import org.service.inter.ProductInter;
import org.service.inter.SaleInter;

public class UseServiceObj {
    public static ProductInter getProductServiceObj(String type){
        if(type.equalsIgnoreCase("database")){
            return new ProductImplDb();
        }else if(type.equalsIgnoreCase("memory")){
            return new ProductImpl();
        }
        return null;
    }
    public static ItemInter getItermServiceObj(String type){
        if(type.equalsIgnoreCase("database")){
            return new ItemImplDb();
        }else if(type.equalsIgnoreCase("memory")){
            return new ItemImpl() ;
        }
        return null;
    }
    public static SaleInter getSaleServiceObj(String type){
        if(type.equalsIgnoreCase("database")){
            return new SaleImplDb();
        }else if(type.equalsIgnoreCase("memory")){
            return new SaleImpl();
        }
        return null;
    }

}
