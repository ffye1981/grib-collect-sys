package com.zgss.grib.gribjson;

import com.zgss.grib.gribjson.util.Launcher;
import org.junit.Test;

public class LauncherTest {

    @Test
    public void main() {
//        Launcher.main(new String[] {"E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\gfs.t12z.sfluxgrbf00.grib2", "out.txt", "true"});
//        Launcher.main(new String[] {"c:/users/cambecc/desktop/gfs/gfs.t18z.pgrbf00.2p5deg.grib2", "out.txt", "false"});
        String args;
//        for(int i= 0;i< 40;i = i + 3) {
//            String index = String.format("%03d", i);
//            args = "--names --data --fc 2 --fp wind --fv 100000 --fs 100 --output E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\gfs.t00z.pgrb2.1p00.f"+index+".json E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\gfs.t00z.pgrb2.1p00.f"+index;
//            Launcher.main(args.split(" "));
//        }

//        String gribName = "E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\gfs.t00z.pgrb2.1p00.f000";
//        String[] surface1Values = new String[]{"10000","20000","30000","50000","70000", "85000", "100000"};
//        //解析风
//        for (String surface1Value: surface1Values) {
//            args = "--names --data --fc 2 --fp wind --fv "+ surface1Value+" --fs 100 --output E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\gfs.t00z.pgrb2.1p00_json\\gfs.t00z.pgrb2.1p00.component_of_wind_"+surface1Value+".json "+gribName;
//            Launcher.main(args.split(" "));
//        }
//        //解析温度
//        for (String surface1Value: surface1Values) {
//            args = "--names --data --fc 0 --fp 0 --fv "+ surface1Value+" --fs 100 --output E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\gfs.t00z.pgrb2.1p00_json\\gfs.t00z.pgrb2.1p00.Temperature_"+surface1Value+".json "+gribName;
//            Launcher.main(args.split(" "));
//        }
//        //解析位势高度
//        for (String surface1Value: surface1Values) {
//            args = "--names --data --fc 3 --fp 5 --fv "+ surface1Value+" --fs 100 --output E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\gfs.t00z.pgrb2.1p00_json\\gfs.t00z.pgrb2.1p00.Geopotential_height_"+surface1Value+".json "+gribName;
//            Launcher.main(args.split(" "));
//        }
//
//        //解析冰水混合比
//        for (String surface1Value: surface1Values) {
//            args = "--names --data --fc 1 --fp 23 --fv "+ surface1Value+" --fs 100 --output E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\gfs.t00z.pgrb2.1p00_json\\gfs.t00z.pgrb2.1p00.Ice_water_mixing_ratio_"+surface1Value+".json "+gribName;
//            Launcher.main(args.split(" "));
//        }
//
//        //解析冰水混合比
//        for (String surface1Value: surface1Values) {
//            args = "--names --data --fc 1 --fp 23 --fv "+ surface1Value+" --fs 100 --output E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\gfs.t00z.pgrb2.1p00_json\\gfs.t00z.pgrb2.1p00.Ice_water_mixing_ratio_"+surface1Value+".json "+gribName;
//            Launcher.main(args.split(" "));
//        }
//
//        //解析可降水量
//        args = "--names --data --fc 1 --fp 3 --fv "+ 0 +" --fs 200 --output E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\gfs.t00z.pgrb2.1p00_json\\gfs.t00z.pgrb2.1p00.Precipitable_water_0.json "+gribName;
//        Launcher.main(args.split(" "));
//
//        //解析相对湿度
//        for (String surface1Value: surface1Values) {
//            args = "--names --data --fc 1 --fp 1 --fv "+ surface1Value+" --fs 100 --output E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\gfs.t00z.pgrb2.1p00_json\\gfs.t00z.pgrb2.1p00.Relative_humidity_"+surface1Value+".json "+gribName;
//            Launcher.main(args.split(" "));
//        }
//
//        //解析总云量
//        for (String surface1Value: surface1Values) {
//            args = "--names --data --fc 6 --fp 1 --fv "+ surface1Value+" --fs 100 --output E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\gfs.t00z.pgrb2.1p00_json\\gfs.t00z.pgrb2.1p00.Total_cloud_cover_"+surface1Value+".json "+gribName;
//            Launcher.main(args.split(" "));
//        }
//
//        //解析能见度
//        args = "--names --data --fc 19 --fp 0 --fv "+ 0 +" --fs 1 --output E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\gfs.t00z.pgrb2.1p00_json\\gfs.t00z.pgrb2.1p00.Visibility_0.json "+gribName;
//        Launcher.main(args.split(" "));
//
//        //解析垂直风
//        args = "--names --data --fc 2 --fp 9 --fv "+ 10000 +" --fs 100 --output E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\gfs.t00z.pgrb2.1p00_json\\gfs.t00z.pgrb2.1p00.Vertical_velocity_10000.json "+gribName;
//        Launcher.main(args.split(" "));
//
//        //解析阵风
//        long start = Calendar.getInstance().getTimeInMillis();
//        args = "--names --data --fc 2 --fp 22 --fv "+ 0 +" --fs 1 --output E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\gfs.t00z.pgrb2.1p00_json\\gfs.t00z.pgrb2.1p00.Wind_speed_gust_0.json "+gribName;
//        Launcher.main(args.split(" "));
//        long delay = (Calendar.getInstance().getTimeInMillis() - start);
//        System.out.println("耗时：" + delay + "毫秒");


//        args = "--names --data --fc 2 --fp wind  E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\温度\\gfs.t00z.pgrb2.1p00.f000";
//        args = "--names --data --output E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\温度\\gfs.t00z.pgrb2.1p00.f000.json E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\温度\\gfs.t00z.pgrb2.1p00.f000";
//        args = "--names --data --output E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\gfs.t00z.pgrb2.1p00.f000.json E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\gfs.t00z.pgrb2.1p00.f000";
//        Launcher.main(args.split(" "));

        String[] params = new String[] {
                "--names","--data","--output","E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\温度\\gfs.t00z.pgrb2.1p00.f000.json","E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\温度\\gfs.t00z.pgrb2.1p00.f000"
        };
        Launcher.main(params);
//          String jsonStr = readJsonFile("E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\gfs.t00z.pgrb2.1p00.f000.json");
//        JsonReader json_text = Json.createReader(new StringReader(jsonStr));
//        JsonArray jobj = json_text.readArray();
//        List<Map> list=new ArrayList<Map>();
//        for (JsonValue json: jobj) {
//            Map<String, String> dataMap=new HashMap<String, String>();
//            JsonObject object = ((JsonObject)((JsonObject)json).get("header"));
//            System.out.println(object.get("parameterCategory").toString() + " "
//                    + object.get("parameterCategoryName") + " "
//                    + object.get("parameterNumber").toString() + " "
//                    + object.get("parameterNumberName").toString() + " "
//                    + object.get("surface1Type").toString() + " "
//                    + object.get("surface1TypeName").toString() + " "
//                    + object.get("surface1Value").toString() + " ");
//
//            dataMap.put("parameterCategory", object.get("parameterCategory").toString());
//            dataMap.put("parameterCategoryName", object.get("parameterCategoryName").toString());
//            dataMap.put("parameterNumber", object.get("parameterNumber").toString());
//            dataMap.put("parameterNumberName", object.get("parameterNumberName").toString());
//            dataMap.put("surface1Type", object.get("surface1Type").toString());
//            dataMap.put("surface1TypeName", object.get("surface1TypeName").toString());
//            dataMap.put("surface1Value", object.get("surface1Value").toString());
//            list.add(dataMap);
//        }
//        WriteExcel.writeExcel(list, 7, "E:\\资料\\紫光陕数\\气象项目\\通用项目\\数据库\\气象参数整理.xlsx");
    }

}