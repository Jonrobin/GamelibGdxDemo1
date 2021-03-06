package kiet.nguyentuan.libgdx.demo1;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by nguye on 10/11/2016.
 */

public class Rock extends AbstractGameObject {
    private TextureRegion regEdge;
    private TextureRegion regMiddle;
    private int length;
    public Rock(){
        init();
    }
    public void init(){
        dimension.set(1,1.5f);
        regEdge= Assets.instance.rock.edge;
        regMiddle=Assets.instance.rock.middle;
        setLength(1);
    }
    public void setLength(int length){
        this.length=length;
    }
    public void increaseLength(int amount){
        setLength(length+amount);
    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion region=null;
        float relX=0;
        float relY=0;
        region=regEdge;
        relX-=dimension.x/4;
        batch.draw(region.getTexture(),position.x+relX,position.y+relY,
                origin.x,origin.y,
                dimension.x/4,dimension.y,
                scale.x,scale.y,
                rotation,
                region.getRegionX(),region.getRegionY(),
                region.getRegionWidth(),region.getRegionHeight(),
                false,
                false);
        relX=0;
        region=regMiddle;
        for(int i=0;i<length;i++){
            batch.draw(region.getTexture(),position.x+relX,position.y+relY,
                    origin.x,origin.y,
                    dimension.x,dimension.y,
                    scale.x,scale.y,
                    rotation,
                    region.getRegionX(),region.getRegionY(),
                    region.getRegionWidth(),region.getRegionHeight(),
                    false,
                    false);
            relX+=dimension.x;
        }
        region=regEdge;
        batch.draw(region.getTexture(),position.x+relX,position.y+relY,
                origin.x+dimension.x/8,origin.y,
                dimension.x/4,dimension.y,
                scale.x,scale.y,
                rotation,
                region.getRegionX(),region.getRegionY(),
                region.getRegionWidth(),region.getRegionHeight(),
                true,
                false);

    }
}
