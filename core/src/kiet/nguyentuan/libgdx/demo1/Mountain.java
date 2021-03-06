package kiet.nguyentuan.libgdx.demo1;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by nguye on 10/11/2016.
 */

public class Mountain extends AbstractGameObject {
    private TextureRegion regMountainLeft;
    private TextureRegion getRegMountainRight;
    private int length;
    public Mountain(int length){
        this.length=length;
        init();
    }

    private void init() {
        dimension.set(10,2);
        regMountainLeft=Assets.instance.levelDecoration.mountainLeft;
        getRegMountainRight=Assets.instance.levelDecoration.mountainRight;
        origin.x=-dimension.x*2;
        length+=dimension.x*2;
    }
    private void drawMountain(SpriteBatch batch,float offsetX,float offsetY,float tintColor){
        TextureRegion reg=null;
        batch.setColor(tintColor,tintColor,tintColor,1);
        float xRel=dimension.x*offsetX;
        float yRel=dimension.y*offsetY;
        int mountainLength=0;
        mountainLength+= MathUtils.ceil(length/(2*dimension.x));
        mountainLength+=MathUtils.ceil(0.5f+offsetX);
        for(int i=0;i<mountainLength;i++){
            reg=regMountainLeft;
            batch.draw(reg.getTexture(), origin.x+xRel,position.y+origin.y+yRel,
                    origin.x,origin.y,
                    dimension.x,dimension.y,
                    scale.x,scale.y,
                    rotation,
                    reg.getRegionX(),reg.getRegionY(),
                    reg.getRegionWidth(),reg.getRegionHeight(),
                    false,
                    false);
            xRel+=dimension.x;

            reg=getRegMountainRight;
            batch.draw(reg.getTexture(), origin.x+xRel,position.y+origin.y+yRel,
                    origin.x,origin.y,
                    dimension.x,dimension.y,
                    scale.x,scale.y,
                    rotation,
                    reg.getRegionX(),reg.getRegionY(),
                    reg.getRegionWidth(),reg.getRegionHeight(),
                    false,
                    false);
            xRel+=dimension.x;
        }
        batch.setColor(1,1,1,1);
    }

    @Override
    public void render(SpriteBatch batch) {
        drawMountain(batch,0.5f,0.5f,0.5f);
        drawMountain(batch,0.25f,0.25f,0.25f);
        drawMountain(batch,0,0,0.9f);
    }
}
