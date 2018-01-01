package com.bw.jiahuweek1.bean;

import java.util.List;

/**
 * Created by 小小云 on 2017/12/30.
 */

public class DishBean {
    /**
     * ret : 1
     * data : [{"id":"8289","title":"油焖大虾","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/9/8289.jpg","collect_num":"1650","food_str":"大虾 葱 生姜 植物油 料酒","num":1650},{"id":"2127","title":"四川回锅肉","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2127.jpg","collect_num":"1584","food_str":"猪肉 青蒜 青椒 红椒 姜片","num":1584},{"id":"30630","title":"超简单芒果布丁","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/31/30630.jpg","collect_num":"1515","food_str":"QQ糖 牛奶 芒果","num":1515},{"id":"9073","title":"家常红烧鱼","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/10/9073.jpg","collect_num":"1416","food_str":"鲜鱼 姜 葱 蒜 花椒","num":1416},{"id":"10097","title":"家常煎豆腐","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/11/10097.jpg","collect_num":"1401","food_str":"豆腐 新鲜红椒 青椒 葱花 油","num":1401},{"id":"10509","title":"水煮肉片","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/11/10509.jpg","collect_num":"1335","food_str":"瘦猪肉 生菜 豆瓣酱 干辣椒 花椒","num":1335},{"id":"46968","title":"红糖苹果银耳汤","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/47/46968.jpg","collect_num":"1242","food_str":"银耳 苹果 红糖","num":1242},{"id":"10191","title":"麻婆豆腐","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/11/10191.jpg","collect_num":"1211","food_str":"豆腐 肉末 生抽 白糖 芝麻油","num":1211},{"id":"2372","title":"皮蛋瘦肉粥","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2372.jpg","collect_num":"1142","food_str":"大米 皮蛋 猪肉 油条 香葱","num":1142},{"id":"2166","title":"蚂蚁上树","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2166.jpg","collect_num":"1135","food_str":"红薯粉 肉 姜 蒜 花椒","num":1135},{"id":"2262","title":"糖醋肉","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2262.jpg","collect_num":"1071","food_str":"猪肉 红椒 黄椒 洋葱 蛋清","num":1071},{"id":"9971","title":"鱼香豆腐","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/10/9971.jpg","collect_num":"1002","food_str":"豆腐 木耳 胡萝卜 香葱 番茄酱","num":1002},{"id":"10172","title":"干煸四季豆","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/11/10172.jpg","collect_num":"987","food_str":"四季豆 干辣椒 蒜头 酱油 糖","num":987},{"id":"2685","title":"胡萝卜肉末蒸蛋","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2685.jpg","collect_num":"910","food_str":"胡萝卜 肉 蛋 生抽 盐","num":910},{"id":"9972","title":"虎皮青椒","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/10/9972.jpg","collect_num":"886","food_str":"青辣椒 大蒜 香醋 白糖 生抽","num":886},{"id":"10437","title":"叉烧排骨","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/11/10437.jpg","collect_num":"793","food_str":"排骨 李锦记叉烧酱 植物油 清水 油菜","num":793},{"id":"2892","title":"\u201c五行\u201d彩蔬汤","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2892.jpg","collect_num":"755","food_str":"黑木耳 玉米 牛蒡 胡萝卜 西兰花","num":755},{"id":"33783","title":"美人豆浆","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/34/33783.jpg","collect_num":"752","food_str":"黄豆 红豆 绿豆 黑豆 黑米","num":752},{"id":"2348","title":"麻辣肉丝面","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/3/2348.jpg","collect_num":"752","food_str":"面条 肉丝 淀粉 酱油 辣椒","num":752},{"id":"10044","title":"土豆炖翅根","pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/11/10044.jpg","collect_num":"750","food_str":"土豆 翅根 葱 姜 料酒","num":750}]
     */

    private int ret;
    private List<DataBean> data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }


}
