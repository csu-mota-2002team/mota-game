package mota1;

import java.awt.Image;

public class Monster extends Stuff{
	  public String name;
      public Image mimage;
      /**
       * 这里我们初始化怪物对象时候需要给予他们需要的属性
       * [url=home.php?mod=space&uid=952169]@Param[/url] paramString 名称
       * @param paramInt1 生命
       * @param paramInt2 攻击
       * @param paramInt3 防御
       * @param paramInt4 价值
       * @param paramInt5 经验
       * @param miImage 图片
       */
      public Monster(String paramString,int paramInt1,int paramInt2,int paramInt3,int paramInt4,int paramInt5,Image miImage) {
              this.name=paramString;//名称
              this.hp=paramInt1;//生命
              this.atk=paramInt2;//攻击
              this.def=paramInt3;//防御
              this.mimage=miImage;//图片
      }
      public String getName() {
              return name;
      }
      public void setName(String name) {
              this.name = name;
      }    
}
