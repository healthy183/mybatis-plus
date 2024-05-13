package org.kang.plus.join;

import com.github.yulichang.toolkit.JoinWrappers;
import com.github.yulichang.wrapper.UpdateJoinWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kang.plus.AppMain;
import org.kang.plus.entity.Actor;
import org.kang.plus.entity.FilmActor;
import org.kang.plus.mapper.ActorMapper;
import org.kang.plus.mapper.FilmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * User:
 * Description:
 * Date: 2024-04-19
 * Time: 17:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
public class UpdateJoinAndNullTest {

    @Autowired
    private ActorMapper actorMapper;

    @Autowired
    private FilmMapper filmMapper;

    @Test
    public void updateJoinAndNull(){
        // PDATE actor t LEFT JOIN film_actor t1 ON (t1.actor_id = t.id)
        //      SET t.name=?, t.update_time=? WHERE (t.id = ?)
        //==> Parameters: locky(String), null, 3(Integer)
        Actor actor = new Actor();
        actor.setName("locky"); // Actor的update_time没有设置，默认置为null
        UpdateJoinWrapper<Actor> wrapper = JoinWrappers.update(Actor.class)
                .leftJoin(FilmActor.class, FilmActor::getActorId, Actor::getId)
                .eq(Actor::getId, 3);
        int i = actorMapper.updateJoinAndNull(actor,wrapper);
        System.out.println("updateJoin count "+ i);
    }

    /**
     * 效果跟updateJoin一样
     */
    @Test
    public void sameUpdate(){
        //  UPDATE actor t LEFT JOIN film_actor t1 ON (t1.actor_id = t.id)
        //  SET t.name=? WHERE (t.id = ?)
        UpdateJoinWrapper<Actor> wrapper = JoinWrappers.update(Actor.class)
                .set(Actor::getName,"ian") // Actor的update_time没有设置也不会设置为null
                //.set(FilmActor::getRemark,"ianRemark")
                .leftJoin(FilmActor.class, FilmActor::getActorId, Actor::getId)
                .eq(Actor::getId, 3);
        int i = actorMapper.updateJoinAndNull(null,wrapper);
        System.out.println("updateJoin count "+ i);
    }

    /**
     * 效果跟updateJoin一样
     */
    @Test
    public void sameUpdate2(){
        // UPDATE actor t LEFT JOIN film_actor t1 ON (t1.actor_id = t.id)
        //  SET t.name=?,t1.remark=? WHERE (t.id = ?)
        UpdateJoinWrapper<Actor> wrapper = JoinWrappers.update(Actor.class)
                .set(Actor::getName,"tom") // Actor的update_time没有设置也不会设置为null
                .set(FilmActor::getRemark,"tomRemark")
                .leftJoin(FilmActor.class, FilmActor::getActorId, Actor::getId)
                .eq(Actor::getId, 3);
        int i = actorMapper.updateJoinAndNull(null,wrapper);
        System.out.println("updateJoin count "+ i);
    }

    @Test
    public void updateJoinAndNullDTO(){
       /* ==>  Preparing: UPDATE actor t LEFT JOIN film_actor t1 ON (t1.actor_id = t.id)
                            SET t.name=?, t.update_time=?, t1.remark=? WHERE (t.id = ?)
        ==> Parameters: hero(String), null, heroRemark(String), 3(Integer)*/
        Actor actor = new Actor();
        actor.setName("hero"); // Actor的update_time没有设置，默认置为null
        UpdateJoinWrapper<Actor> wrapper = JoinWrappers.update(Actor.class)
                .set(FilmActor::getRemark,"heroRemark") // 只更新remark
                .leftJoin(FilmActor.class, FilmActor::getActorId, Actor::getId)
                .eq(Actor::getId, 3);
        int i = actorMapper.updateJoinAndNull(actor,wrapper);
        System.out.println("updateJoin count "+ i);
    }


    @Test
    public void updateJoinAndNullSetUpdateEntity(){
       // ==>  Preparing: UPDATE actor t LEFT JOIN film_actor t1 ON (t1.actor_id = t.id)
        // SET t.name=?,
        // t.update_time=?,
        // t1.remark=?
        // WHERE (t.id = ?)
        //  ==> Parameters: AK(String), null, AKremakr(String), 3(Integer)

        Actor actor = new Actor();
        actor.setName("AK"); // Actor的update_time没有设置，默认置为null
        FilmActor filmActor = new FilmActor();
        filmActor.setRemark("AKremakr"); // 只更新remark
        UpdateJoinWrapper<Actor> wrapper = JoinWrappers.update(Actor.class)
                .setUpdateEntity(filmActor)
                .leftJoin(FilmActor.class, FilmActor::getActorId, Actor::getId)
                .eq(Actor::getId, 3);
        int i = actorMapper.updateJoinAndNull(actor,wrapper);
        System.out.println("updateJoin count "+ i);
    }

    @Test
    public void updateJoinAndNullsetUpdateEntityAndNull(){
        // ==>  Preparing: UPDATE actor t LEFT JOIN film_actor t1 ON (t1.actor_id = t.id)
        // SET t.name=?, t.update_time=?,
        //     t1.film_id=?,t1.actor_id=?,t1.remark=?
        //     WHERE (t.id = ?)
        // ==> Parameters: AK(String), null, null, null, AKremakr(String), 3(Integer)
        Actor actor = new Actor();
        actor.setName("AK"); // Actor的update_time没有设置，默认置为null
        FilmActor filmActor = new FilmActor();
        filmActor.setRemark("AKremakr"); // 不止更新remark，其他字段都设null
        UpdateJoinWrapper<Actor> wrapper = JoinWrappers.update(Actor.class)
                .setUpdateEntityAndNull(filmActor)
                .leftJoin(FilmActor.class, FilmActor::getActorId, Actor::getId)
                .eq(Actor::getId, 3);
        int i = actorMapper.updateJoinAndNull(actor,wrapper);
        System.out.println("updateJoin count "+ i);
    }

}
