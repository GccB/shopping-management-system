package lyons.dao;

import java.io.IOException;
import java.util.List;

import lyons.db.DbAccess;
import lyons.goods.entity.Goods;

import org.apache.ibatis.session.SqlSession;

/**
 * 商品维护类-学习面向接口编程
 * 
 * commodity.sql
 */
public class GoodsDaoImpl implements GoodsDao
{

    GoodsDao goodsDao;
	DbAccess dbAccess;
	SqlSession sqlSession;
	
	public GoodsDaoImpl()
    {
	    dbAccess = new DbAccess();
    }
	/**
     * 
     * 查询商品列表-commodity.sql
     * 根据（关键字||分类）or（关键字&&分类）
     * 
     * 此查询函数不再改为接口式编程，作为知识留存
     * 
     * @return 商品列表数组
     * 
     */
    public List<Goods> queryGoodsByKeyClassify(Goods goodsList)
    {
        try
        {
            sqlSession = dbAccess.getSqlSession();
            return sqlSession.selectList("lyons.dao.GoodsDao.queryGoodsByKeyClassify", goodsList);//空间名.查询语句id 
//                                      (留此方式目的完成知识体系，其他的均使用mapper)
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            sqlSesionClose();
        }
        
        return null;
    }
	
	/**
	 * 
	 * 查询商品列表-commodity.sql
	 * @return 商品列表数组
	 * 
	 */
	public List<Goods> goodsAllList()
    {
	    try
        {
            sqlSession = dbAccess.getSqlSession();
            goodsDao = sqlSession.getMapper(GoodsDao.class);
            return goodsDao.goodsAllList();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            sqlSesionClose();
        }
        
        return null;
    }
	/**
     * 
     * 查询商品列表-commodity.sql
     * 根据关键字查询
     * @return 商品列表数组
     * 
     */
	public List<Goods> queryGoodsByKey(String keyWord)
	{
	    try
        {
            sqlSession = dbAccess.getSqlSession();
            goodsDao =  sqlSession.getMapper(GoodsDao.class);
            return goodsDao.queryGoodsByKey(keyWord);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            sqlSesionClose();
        }
	    
	    return null;
	}
	

    
    
	
    
    /**
     * 删除单个商品
     * By Goods ID
     */
    public void deleteOneGoodsById(int goodsId)
    {
        try
        {
            sqlSession = dbAccess.getSqlSession();
            
            sqlSession.getMapper(GoodsDao.class).deleteOneGoodsById(goodsId);
            sqlSession.commit();//默认不自动提交，需要手工提交
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            sqlSesionClose();
        }
        
    }
    
    /**
     * 批量删除商品
     */
    public void deleteGoodsByMany()
    {
        
    }
    
    /**
     * 增加商品
     */
    public void addGoods()
    {
        
    }
    
    /**
     * 更新商品
     * 购买商品
     */
    public void updateGoods(List<Goods> listgoods)
    {
        try
        {
            sqlSession = dbAccess.getSqlSession();
            sqlSession.getMapper(GoodsDao.class).updateGoods(listgoods);
            sqlSession.commit();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            sqlSesionClose();
        }
        
    }
    
    
	/*
	 * 关闭数据库连接会话
	 */
	private void sqlSesionClose()
    {
	    if (sqlSession != null)
        {
            sqlSession.close();
        }
    }
	
}