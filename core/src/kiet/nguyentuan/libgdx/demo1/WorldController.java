package kiet.nguyentuan.libgdx.demo1;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by nguye on 03/11/2016.
 */
public class WorldController extends InputAdapter {

    private static String TAG=WorldController.class.getName();
    public Sprite[] testSprites;
    public int selectedSprite;
    public CameraHelper cameraHelper;

    public WorldController(){
        init();
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode== Input.Keys.R){
            init();
            Gdx.app.debug(TAG,"Game restart");
        }
        if(keycode== Input.Keys.SPACE){
            selectedSprite =(selectedSprite+1)%testSprites.length;
            if(cameraHelper.hasTarget()){
                cameraHelper.setTarget(testSprites[selectedSprite]);
            }
            Gdx.app.debug(TAG,"Sprite #"+selectedSprite+" selected");
        }
        if(keycode== Input.Keys.ENTER){
            cameraHelper.setTarget(cameraHelper.hasTarget() ? null:testSprites[selectedSprite]);
            Gdx.app.debug(TAG,"Camera follow enabled: "+cameraHelper.hasTarget());
        }
        return super.keyUp(keycode);
    }

    private void init(){
        Gdx.input.setInputProcessor(this);
        cameraHelper=new CameraHelper();
        initTestObjects();
    }

    private void initTestObjects() {
        testSprites=new Sprite[7];
        int width=32;
        int height=32;
        Pixmap pixmap=createProceduralPixmap(width,height);
        Texture texture=new Texture(pixmap);
        for(int i=0;i<testSprites.length;i++){
            Sprite spr=new Sprite(texture);
            spr.setSize(1,1);
            spr.setOrigin(spr.getWidth()/2f,spr.getHeight()/2f);
            float randomX= MathUtils.random(-2f,2f);
            float randomY=MathUtils.random(-2f,2f);
            spr.setPosition(randomX,randomY);
            testSprites[i]=spr;
        }
        selectedSprite=0;
    }

    private Pixmap createProceduralPixmap(int width, int height) {
        Pixmap pixmap=new Pixmap(width,height, Pixmap.Format.RGBA8888);
        pixmap.setColor(1,0,0,0.5f);
        pixmap.fill();
        pixmap.setColor(1,1,0,1);
        pixmap.drawLine(0,0,width,height);
        pixmap.drawLine(width,0,0,height);
        pixmap.setColor(0,1,1,1);
        pixmap.drawRectangle(0,0,width,height);
        return pixmap;
    }


    public void update(float deltaTime){
        handleDebugInput(deltaTime);
        updateTestObjects(deltaTime);
        cameraHelper.update(deltaTime);
    }

    private void handleDebugInput(float deltaTime) {

        if(Gdx.app.getType() != Application.ApplicationType.Desktop)
            return;

        float sprMoveSpeed=5*deltaTime;
        if(Gdx.input.isKeyPressed(Input.Keys.A))
            moveSelectedSprite(-sprMoveSpeed,0);
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            moveSelectedSprite(sprMoveSpeed,0);
        if(Gdx.input.isKeyPressed(Input.Keys.W))
            moveSelectedSprite(0,sprMoveSpeed);
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            moveSelectedSprite(0,-sprMoveSpeed);

        float camMoveSpeed=5*deltaTime;
        float cameMoveAccelFactor=5;
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
            camMoveSpeed*=cameMoveAccelFactor;
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            moveCamera(-camMoveSpeed,0);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            moveCamera(camMoveSpeed,0);
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            moveCamera(0,camMoveSpeed);
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            moveCamera(0,-camMoveSpeed);
        if(Gdx.input.isKeyPressed(Input.Keys.BACKSPACE))
            moveCamera(0,0);

        float camZoomSpeed =1*deltaTime;
        float camZoomAccelFactor=5;
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
            camZoomSpeed*=camZoomAccelFactor;
        if(Gdx.input.isKeyPressed(Input.Keys.COMMA))
            cameraHelper.addZoom(camZoomSpeed);
        if(Gdx.input.isKeyPressed(Input.Keys.PERIOD))
            cameraHelper.addZoom(-camZoomSpeed);
        if(Gdx.input.isKeyPressed(Input.Keys.SLASH))
            cameraHelper.addZoom(1);

    }

    private void moveCamera(float x,float y) {
        x+=cameraHelper.getPosition().x;
        y+=cameraHelper.getPosition().y;
        cameraHelper.setPosition((float)x,(float)y);
    }

    private void moveSelectedSprite(float x, float y) {
        testSprites[selectedSprite].translate((float)x,(float)y);
    }

    private void updateTestObjects(float deltaTime) {
            float rotation = testSprites[selectedSprite].getRotation();
            rotation += 90 * deltaTime;
            rotation %= 360;
            testSprites[selectedSprite].setRotation(rotation);
    }
}
