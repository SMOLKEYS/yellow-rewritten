package yellow.content;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.graphics.*;
import mindustry.ui.*;
import yellow.entities.effect.*;


public class YellowFx{
    
    public static final Effect

    ghostDespawn = new Effect(10f, e -> {
        Draw.z(Layer.effect);
        Draw.alpha(e.fout() * 3);
        
        Lines.stroke(e.fout() * 7);
        Lines.circle(e.x, e.y, e.fin() * 10);
    }),

    ghostDespawn2 = new Effect(15f, e -> {
        Draw.z(Layer.effect);
        Draw.alpha(e.fout() * 3);

        Lines.stroke(e.fout() * 7);
        Lines.circle(e.x, e.y, e.fin() * 17);
    }),

    ghostDespawn3 = new Effect(15f, e -> {
        Draw.z(Layer.effect);
        Draw.alpha(e.fout() * 3);

        Lines.stroke(e.fout() * 7);
        Lines.circle(e.x, e.y, e.fin() * 23);
    }),

    ghostDespawnMulti = new RandomEffect(ghostDespawn, ghostDespawn2, ghostDespawn3),

    yellowExplosionOut = new Effect(120f, e -> {
        Draw.color(Color.yellow);

        float h = e.fin(Interp.smooth);
        
        Lines.stroke(e.foutpow() * 15);
        Lines.circle(e.x, e.y, h * 25);
        Lines.square(e.x, e.y, h * 50, Time.time * 9);
        Lines.circle(e.x, e.y, h * 50);
        Lines.square(e.x, e.y, h * 100, Time.time * 9);
        Lines.circle(e.x, e.y, h * 25);
        Lines.square(e.x, e.y, h * 50, -Time.time * 9);
        Lines.circle(e.x, e.y, h * 50);
        Lines.square(e.x, e.y, h * 100, -Time.time * 9);
    }),

    yellowExplosionIn = new Effect(120f, e -> {
        Draw.color(Color.yellow);
        
        Lines.stroke(e.finpow() * 15);
        Lines.circle(e.x, e.y, e.foutpow() * 25);
        Lines.square(e.x, e.y, e.foutpow() * 50, Time.time * 9);
        Lines.circle(e.x, e.y, e.foutpow() * 50);
        Lines.square(e.x, e.y, e.foutpow() * 100, Time.time * 9);
        Lines.circle(e.x, e.y, e.foutpow() * 25);
        Lines.square(e.x, e.y, e.foutpow() * 50, -Time.time * 9);
        Lines.circle(e.x, e.y, e.foutpow() * 50);
        Lines.square(e.x, e.y, e.foutpow() * 100, -Time.time * 9);
    }),

    yellowDeathEffect = new Effect(210f, e -> {
        Draw.color(Color.yellow, Color.orange, e.finpow());
        
        Lines.stroke(e.fout() * 5);
        Lines.square(e.x, e.y, e.finpow() * 90, e.finpow() * 180);
        Lines.square(e.x, e.y, e.finpow() * 90, e.foutpow() * 180);
        Lines.circle(e.x, e.y, e.finpow() * 130);
        Lines.circle(e.x, e.y, e.finpow() * 120);
        Lines.circle(e.x, e.y, e.finpow() * 110);

        Angles.randLenVectors(e.id, 50, e.finpow() * 100, (x, y) -> {
            Lines.stroke(1);
            Draw.color(Color.yellow, Color.orange, e.finpow());
            Fill.circle(e.x + x, e.y + y, e.fout() * 10);
        });

        Draw.alpha(e.fout() * 4);
        Draw.rect("yellow-yellow", e.x, e.y, e.finpow() * 200, e.finpow() * 200);
    }),
    
    despawn = new Effect(120f, e -> {
        Lines.stroke(e.fout() * 6f);
        
        Draw.color(Color.yellow, Color.white, e.fin());
        Lines.line(e.x, e.y - 2000f, e.x, e.y + 2000f);
    }),


    fireCircleEffect = new Effect(180f, e -> {
        Draw.z(Layer.effect);
        Lines.stroke(e.fout() * 40);

        Draw.color(Pal.lightFlame, Color.orange, e.fin());
        Lines.circle(e.x, e.y, e.finpow() * 8*22);

        Angles.randLenVectors(e.id, 40, e.finpow() * 200, (x, y) -> {
            Fx.fire.at(e.x + x, e.y + y);
        });

        Angles.randLenVectors(e.id, 100, e.finpow() * 280, (x, y) -> {
            Draw.color(Color.gray);
            Fill.circle(e.x + x, e.y + y, e.foutpow() * 30);
        });
    }),

    permissionDenied = new Effect(240f, e -> {
        Draw.z(Layer.endPixeled);
        Fonts.def.draw("<< PERMISSION DENIED >>", e.x, e.y, Pal.remove, 1f, false, Align.center);
    });
}
