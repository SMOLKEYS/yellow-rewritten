package yellow.entities.units.entity;

import arc.func.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.geom.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.io.*;
import java.nio.*;
import mindustry.ai.types.*;
import mindustry.ctype.*;
import mindustry.entities.*;
import mindustry.entities.units.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.logic.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.storage.*;

//yep.
public final class NullEntity extends UnitEntity{

    public static final NullEntity INST = new NullEntity();

    private NullEntity(){
    }


    @Override
    public TextureRegion icon(){
        return null;
    }

    @Override
    public float physicSize(){
        return 0;
    }

    @Override
    public String getControllerName(){
        return "";
    }

    @Override
    public int pathType(){
        return 0;
    }

    @Override
    public float speed(){
        return 0;
    }

    @Override
    public float prefRotation(){
        return 0;
    }

    @Override
    public boolean canLand(){
        return false;
    }

    @Override
    public void destroy(){

    }

    @Override
    public void unloaded(){

    }

    @Override
    public void movePref(Vec2 vec2){

    }

    @Override
    public Player getPlayer(){
        return null;
    }

    @Override
    public boolean canShoot(){
        return false;
    }

    @Override
    public boolean isRotate(){
        return false;
    }

    @Override
    public float ammof(){
        return 0;
    }

    @Override
    public void aim(Position position){

    }

    @Override
    public void controlWeapons(boolean b, boolean b1){

    }

    @Override
    public void controlWeapons(boolean b){

    }

    @Override
    public void setWeaponRotation(float v){

    }

    @Override
    public void setupWeapons(UnitType unitType){

    }

    @Override
    public boolean displayable(){
        return false;
    }

    @Override
    public boolean hasWeapons(){
        return false;
    }

    @Override
    public boolean hittable(){
        return false;
    }

    @Override
    public boolean inRange(Position position){
        return false;
    }

    @Override
    public boolean isAI(){
        return false;
    }

    @Override
    public boolean isCommandable(){
        return false;
    }

    @Override
    public boolean isEnemy(){
        return false;
    }

    @Override
    public boolean isPathImpassable(int i, int i1){
        return false;
    }

    @Override
    public boolean isPlayer(){
        return false;
    }

    @Override
    public boolean targetable(Team team){
        return false;
    }

    @Override
    public double sense(Content content){
        return 0;
    }

    @Override
    public double sense(LAccess lAccess){
        return 0;
    }

    @Override
    public float bounds(){
        return 0;
    }

    @Override
    public float range(){
        return 0;
    }

    @Override
    public int cap(){
        return 0;
    }

    @Override
    public int count(){
        return 0;
    }

    @Override
    public Object senseObject(LAccess lAccess){
        return null;
    }

    @Override
    public CommandAI command(){
        return null;
    }

    @Override
    public UnitController controller(){
        return null;
    }

    @Override
    public void aimLook(Position position){

    }

    @Override
    public void aimLook(float v, float v1){

    }

    @Override
    public void approach(Vec2 vec2){

    }

    @Override
    public void controller(UnitController unitController){

    }

    @Override
    public void display(Table table){

    }

    @Override
    public void lookAt(Position position){

    }

    @Override
    public void lookAt(float v){

    }

    @Override
    public void lookAt(float v, float v1){

    }

    @Override
    public void moveAt(Vec2 vec2){

    }

    @Override
    public void resetController(){

    }

    @Override
    public void rotateMove(Vec2 vec2){

    }

    @Override
    public void set(UnitType unitType, UnitController unitController){

    }

    @Override
    public void setProp(UnlockableContent unlockableContent, double v){

    }

    @Override
    public void setProp(LAccess lAccess, double v){

    }

    @Override
    public void setProp(LAccess lAccess, Object o){

    }

    @Override
    public void setType(UnitType unitType){

    }

    @Override
    public void updateBoosting(boolean b){

    }

    @Override
    public BuildPlan buildPlan(){
        return null;
    }

    @Override
    public boolean shouldSkip(BuildPlan buildPlan, Building building){
        return false;
    }

    @Override
    public void addBuild(BuildPlan buildPlan, boolean b){

    }

    @Override
    public void addBuild(BuildPlan buildPlan){

    }

    @Override
    public void clearBuilding(){

    }

    @Override
    public void drawBuildPlans(){

    }

    @Override
    public boolean isBuilding(){
        return false;
    }

    @Override
    public boolean activelyBuilding(){
        return false;
    }

    @Override
    public boolean canBuild(){
        return false;
    }

    @Override
    public float clipSize(){
        return 0;
    }

    @Override
    public void apply(StatusEffect statusEffect, float v){

    }

    @Override
    public void apply(StatusEffect statusEffect){

    }

    @Override
    public void unapply(StatusEffect statusEffect){

    }

    @Override
    public Color statusColor(){
        return null;
    }

    @Override
    public Bits statusBits(){
        return null;
    }

    @Override
    public boolean hasEffect(StatusEffect statusEffect){
        return false;
    }

    @Override
    public boolean isBoss(){
        return false;
    }

    @Override
    public boolean isImmune(StatusEffect statusEffect){
        return false;
    }

    @Override
    public float getDuration(StatusEffect statusEffect){
        return 0;
    }

    @Override
    public void clearStatuses(){

    }

    @Override
    public boolean canMine(){
        return false;
    }

    @Override
    public boolean canMine(Item item){
        return false;
    }

    @Override
    public boolean mining(){
        return false;
    }

    @Override
    public boolean offloadImmediately(){
        return false;
    }

    @Override
    public boolean validMine(Tile tile){
        return false;
    }

    @Override
    public boolean validMine(Tile tile, boolean b){
        return false;
    }

    @Override
    public Item getMineResult(Tile tile){
        return null;
    }

    @Override
    public void draw(){

    }

    @Override
    public void drawBuilding(){

    }

    @Override
    public void drawBuildingBeam(float v, float v1){

    }

    @Override
    public void drawPlan(BuildPlan buildPlan, float v){

    }

    @Override
    public void drawPlanTop(BuildPlan buildPlan, float v){

    }

    @Override
    public void removeBuild(int i, int i1, boolean b){

    }

    @Override
    public void aim(float v, float v1){

    }

    @Override
    public boolean canDrown(){
        return false;
    }

    @Override
    public boolean checkTarget(boolean b, boolean b1){
        return false;
    }

    @Override
    public boolean emitWalkSound(){
        return false;
    }

    @Override
    public boolean isFlying(){
        return false;
    }

    @Override
    public boolean isGrounded(){
        return false;
    }

    @Override
    public float floorSpeedMultiplier(){
        return 0;
    }

    @Override
    public Floor drownFloor(){
        return null;
    }

    @Override
    public void landed(){

    }

    @Override
    public void moveAt(Vec2 vec2, float v){

    }

    @Override
    public void damagePierce(float v){

    }

    @Override
    public void damagePierce(float v, boolean b){

    }

    @Override
    public void rawDamage(float v){

    }

    @Override
    public void healFract(float v){

    }

    @Override
    public void heal(float v){

    }

    @Override
    public boolean damaged(){
        return false;
    }

    @Override
    public boolean isValid(){
        return false;
    }

    @Override
    public float healthf(){
        return 0;
    }

    @Override
    public void clampHealth(){

    }

    @Override
    public void damage(float v){

    }

    @Override
    public void damage(float v, boolean b){

    }

    @Override
    public void damageContinuous(float v){

    }

    @Override
    public void damageContinuousPierce(float v){

    }

    @Override
    public void heal(){

    }

    @Override
    public void kill(){

    }

    @Override
    public void killed(){

    }

    @Override
    public boolean collides(Hitboxc hitboxc){
        return false;
    }

    @Override
    public float deltaAngle(){
        return 0;
    }

    @Override
    public float deltaLen(){
        return 0;
    }

    @Override
    public float hitSize(){
        return 0;
    }

    @Override
    public void collision(Hitboxc hitboxc, float v, float v1){

    }

    @Override
    public void getCollisions(Cons<QuadTree> cons){

    }

    @Override
    public void hitbox(Rect rect){

    }

    @Override
    public void hitboxTile(Rect rect){

    }

    @Override
    public boolean acceptsItem(Item item){
        return false;
    }

    @Override
    public boolean hasItem(){
        return false;
    }

    @Override
    public int itemCapacity(){
        return 0;
    }

    @Override
    public int maxAccepted(Item item){
        return 0;
    }

    @Override
    public Item item(){
        return null;
    }

    @Override
    public void addItem(Item item){

    }

    @Override
    public void addItem(Item item, int i){

    }

    @Override
    public void clearItem(){

    }

    @Override
    public EntityCollisions.SolidPred solidity(){
        return null;
    }

    @Override
    public boolean canPassOn(){
        return false;
    }

    @Override
    public boolean canPass(int i, int i1){
        return false;
    }

    @Override
    public boolean moving(){
        return false;
    }

    @Override
    public void move(Vec2 vec2){

    }

    @Override
    public void move(float v, float v1){

    }

    @Override
    public boolean isSyncHidden(Player player){
        return false;
    }

    @Override
    public void afterSync(){

    }

    @Override
    public void handleSyncHidden(){

    }

    @Override
    public void interpolate(){

    }

    @Override
    public void readSync(Reads reads){

    }

    @Override
    public void readSyncManual(FloatBuffer floatBuffer){

    }

    @Override
    public void snapInterpolation(){

    }

    @Override
    public void snapSync(){

    }

    @Override
    public <T extends Entityc> T self(){
        return null;
    }

    @Override
    public <T> T as(){
        return null;
    }

    @Override
    public boolean isAdded(){
        return false;
    }

    @Override
    public boolean isLocal(){
        return false;
    }

    @Override
    public boolean isNull(){
        return false;
    }

    @Override
    public boolean isRemote(){
        return false;
    }

    @Override
    public boolean serialize(){
        return false;
    }

    @Override
    public int classId(){
        return 0;
    }

    @Override
    public void add(){

    }

    @Override
    public void afterRead(){

    }

    @Override
    public void read(Reads reads){

    }

    @Override
    public void remove(){

    }

    @Override
    public void update(){

    }

    @Override
    public void write(Writes writes){

    }

    @Override
    public void writeSync(Writes writes){

    }

    @Override
    public void writeSyncManual(FloatBuffer floatBuffer){

    }

    @Override
    public void velAddNet(Vec2 vec2){

    }

    @Override
    public void velAddNet(float v, float v1){

    }

    @Override
    public void updateLastPosition(){

    }

    @Override
    public void updateDrowning(){

    }

    @Override
    public void wobble(){

    }

    @Override
    public void updateBuildLogic(){

    }

    @Override
    public void validatePlans(){

    }

    @Override
    public float mass(){
        return 0;
    }

    @Override
    public void impulse(Vec2 vec2){

    }

    @Override
    public void impulse(float v, float v1){

    }

    @Override
    public void impulseNet(Vec2 vec2){

    }

    @Override
    public boolean inFogTo(Team team){
        return false;
    }

    @Override
    public boolean cheating(){
        return false;
    }

    @Override
    public CoreBlock.CoreBuild closestCore(){
        return null;
    }

    @Override
    public CoreBlock.CoreBuild closestEnemyCore(){
        return null;
    }

    @Override
    public CoreBlock.CoreBuild core(){
        return null;
    }

    @Override
    public Floor floorOn(){
        return null;
    }

    @Override
    public Building buildOn(){
        return null;
    }

    @Override
    public boolean onSolid(){
        return false;
    }

    @Override
    public float getX(){
        return 0;
    }

    @Override
    public float getY(){
        return 0;
    }

    @Override
    public float angleTo(Position other){
        return super.angleTo(other);
    }

    @Override
    public float angleTo(float x, float y){
        return super.angleTo(x, y);
    }

    @Override
    public float dst2(Position other){
        return super.dst2(other);
    }

    @Override
    public float dst(Position other){
        return super.dst(other);
    }

    @Override
    public float dst(float x, float y){
        return super.dst(x, y);
    }

    @Override
    public float dst2(float x, float y){
        return super.dst2(x, y);
    }

    @Override
    public boolean within(Position other, float dst){
        return super.within(other, dst);
    }

    @Override
    public boolean within(float x, float y, float dst){
        return super.within(x, y, dst);
    }

    @Override
    public int tileX(){
        return 0;
    }

    @Override
    public int tileY(){
        return 0;
    }

    @Override
    public Block blockOn(){
        return null;
    }

    @Override
    public Tile tileOn(){
        return null;
    }

    @Override
    public void set(Position position){

    }

    @Override
    public void set(float v, float v1){

    }

    @Override
    public void trns(Position position){

    }

    @Override
    public void trns(float v, float v1){

    }
}
