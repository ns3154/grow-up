package com.example.demo.p;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/08/20 18:38
 **/
public class ParkingVO {

    String title;

    Double[] core;

    List<Double[]> regions;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double[] getCore() {
        return core;
    }

    public void setCore(Double[] core) {
        this.core = core;
    }

    public List<Double[]> getRegions() {
        return regions;
    }

    public void setRegions(List<Double[]> regions) {
        this.regions = regions;
    }

    private static ParkingVO natural() {
        List<Double[]> regions = new ArrayList<>();
        regions.add(new Double[]{116.533876, 39.922016});
        regions.add(new Double[]{116.534429, 39.922028});
        regions.add(new Double[]{116.53445, 39.921894});
        regions.add(new Double[]{116.534059, 39.921875});
        regions.add(new Double[]{116.534056, 39.921284});
        regions.add(new Double[]{116.53386, 39.921284});
        Double[] core = {116.534804, 39.916341};
        ParkingVO pv = new ParkingVO();
        pv.setTitle("停车网点-带中心点");
        pv.setCore(core);
        pv.setRegions(regions);
        return pv;
    }

    private static ParkingVO defect() {
        List<Double[]> regions = new ArrayList<>();
        regions.add(new Double[]{116.538848, 39.913608});
        regions.add(new Double[]{116.53894, 39.913612});
        regions.add(new Double[]{116.538848, 39.913608});
        regions.add(new Double[]{116.538961, 39.913279});
        regions.add(new Double[]{116.538864, 39.913267});
        Double[] core = {};
        ParkingVO pv = new ParkingVO();
        pv.setTitle("停车网点-无中心点");
        pv.setCore(core);
        pv.setRegions(regions);
        return pv;
    }

    public static void main(String[] args) {
        List<ParkingVO> list = new ArrayList<>();
        list.add(natural());
        list.add(defect());

        Model<List<ParkingVO>> model = new Model<>();
        model.setCode(200);
        model.setMessage("ok");
        model.setData(list);

        System.out.println(JSON.toJSONString(model));
    }
}
