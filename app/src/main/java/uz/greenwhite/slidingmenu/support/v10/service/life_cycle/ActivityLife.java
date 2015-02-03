package uz.greenwhite.slidingmenu.support.v10.service.life_cycle;

public final class ActivityLife {

    public static final ActivityLife instance = new ActivityLife();

    private int lifeCycle;

    public void onStart() {
        this.lifeCycle = LifeCycle.START;
    }

    public void onStop() {
        this.lifeCycle = LifeCycle.STOP;
    }

    public int getState() {
        return this.lifeCycle;
    }
}
