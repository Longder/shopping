package com.longder.shopping.service.impl;

import com.longder.shopping.entity.po.Goods;
import com.longder.shopping.entity.po.SysUser;
import com.longder.shopping.repository.GoodsRepository;
import com.longder.shopping.service.GoodsManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.util.List;

@Service
public class GoodsManageServiceImpl implements GoodsManageService {

    @Resource
    private GoodsRepository goodsRepository;

    /**
     * 根据用户查询商品列表
     *
     * @param sysUser
     * @return
     */
    @Override
    public List<Goods> listGoods(SysUser sysUser) {
        return goodsRepository.listByUser(sysUser);
    }

    /**
     * 查询所有商品
     *
     * @return
     */
    @Override
    public List<Goods> listAllGoods() {
        return goodsRepository.findAll();
    }

    /**
     * 保存一个商品（新增修改都走）
     *
     * @param goods
     * @param seller
     */
    @Override
    @Transactional
    public void saveOneGoods(Goods goods, SysUser seller) {
        if(ObjectUtils.isEmpty(goods.getId())){//新增
            //图片转为base64
            try {
                goods.setImage("data:image/jpeg;base64,"+DatatypeConverter.printBase64Binary(goods.getImageFile().getBytes()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            //记录卖家，当前用户
            goods.setSeller(seller);
            goodsRepository.save(goods);
        }else{//修改
            Goods dbGoods = goodsRepository.getOne(goods.getId());
            dbGoods.setName(goods.getName());
            dbGoods.setType(goods.getType());
            dbGoods.setUnitPrice(goods.getUnitPrice());
            dbGoods.setDescribe(goods.getDescribe());
            //上传了图片才修改图片
            if(!ObjectUtils.isEmpty(goods.getImageFile().getOriginalFilename())){
                try {
                    dbGoods.setImage("data:image/jpeg;base64,"+DatatypeConverter.printBase64Binary(goods.getImageFile().getBytes()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            goodsRepository.save(dbGoods);
        }
    }

    /**
     * 查询获取一个商品
     *
     * @param goodsId
     * @return
     */
    @Override
    public Goods getOneGoods(Long goodsId) {
        return goodsRepository.getOne(goodsId);
    }

    /**
     * 修改商品库存
     *
     * @param goods
     */
    @Override
    @Transactional
    public void updateGoodsCount(Goods goods) {
        Goods dbGoods = goodsRepository.getOne(goods.getId());
        dbGoods.setCount(goods.getCount());
        goodsRepository.save(dbGoods);
    }
}

