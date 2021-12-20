package com.qitai.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.TreeSet;

/**
 * @apiDefine TreeEntity 树节点对象
 * @apiSuccess {String} .id 节点id
 * @apiSuccess {String} .text 显示文本
 * @apiSuccess {String} .iconCls 显示节点图标className
 * @apiSuccess {String} .state 显示状态（open、closed）
 * @apiSuccess {String} .parentId 所在父节点id
 * @apiSuccess {int} .sort 排序值
 * @apiSuccess {TreeEntity_list} .children 子节点
 * @apiSuccess {object} .attributes 扩展属性（依据调用而不同，见下述）
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeEntity implements Comparable<com.qitai.utils.TreeEntity>
{
	/**
	 * 节点收缩状态标识
	 */
	public static final String STATE_CLOSED = "closed";
	/**
	 * 节点展开状态标识
	 */
	public static final String STATE_OPEN = "open";
	
	/**
	 * 空图标的样式
	 */
	public static final String NULL_ICON="icon-blank";
	/**
	 * 文件夹图标标式
	 */
	public static final String FOLDER_ICON="tree-folder";
	/**
	 * 节点id
	 */
	private String id;
	/**
	 * 节点显示文本
	 */
	private String text;
	
	/**
	 * 节点显示的图标
	 */
	private String iconCls;
	
	/**
	 * 节点默认状态（open、closed），
	 * 如果为open，且没有children属性，则展示为叶子节点；
	 * 如果为closed，且没有children属性，则在展开时会发送加载节点的请求(请求中会添加id参数【Form Data数据】，指明当前点击展开的节点)。
	 */
	private String state;
	
	/*
	 * 父节点标识值
	 */
	private String parentId;

	/**
	 * 排序值
	 */
	private int sort;
	/*
	 * 用于内部标识，此节点是否已处理过
	 */
	@JsonIgnore
	private boolean load;
	
	/**
	 * 节点自定义属性
	 */
	private Object attributes;
	
	/**
	 * 子节点集
	 */
	private TreeSet<com.qitai.utils.TreeEntity> children;

	public TreeEntity()
	{
		children=new TreeSet<com.qitai.utils.TreeEntity>();
	}
	
	/**
	 * 构造节点
	 * @param sId
	 * @param sText
	 * @param sParentId
	 * @param iSort
	 * @param sIcon 节点图标样式定义（null表示默认图标，""表示使用空图标）
	 */
	public TreeEntity(String sId,String sText,String sParentId,int iSort,String sIcon)
	{
		this();
		id=sId;
		text=sText;
		parentId=sParentId;
		sort=iSort;
		if(sIcon!=null){
			if(sIcon.isEmpty())
				sIcon=NULL_ICON;
			iconCls=sIcon;
		}
	}

	@Override
	public int compareTo(com.qitai.utils.TreeEntity o)
	{
		if(o==null) return 1;
		if(this==o) return 0;//如果是同一对象，返回0（用于移除）
  	//注意：不能返回0，因为在TreeSet排序时，如果比较是0值时，会认为是在同一个位置放置元素（将会覆盖原来的元素）
		if(this.sort==o.sort){//如果排序值相同，再比较标题
			String stext=this.text;
			if(stext==null)stext="";
			int ir=stext.compareTo(o.text==null?"":o.text);
			if(ir==0){
				stext=this.id;
				if(stext==null)stext="";
				ir=stext.compareTo(o.id==null?"":o.id);
			}
			return ir==0?1:ir;//仍相等，返回大于
		}
		return (this.sort<o.sort)?-1:1;
	}

	public static String getStateClosed() {
		return STATE_CLOSED;
	}

	public static String getStateOpen() {
		return STATE_OPEN;
	}

	public static String getNullIcon() {
		return NULL_ICON;
	}

	public static String getFolderIcon() {
		return FOLDER_ICON;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public boolean isLoad() {
		return load;
	}

	public void setLoad(boolean load) {
		this.load = load;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public TreeSet<com.qitai.utils.TreeEntity> getChildren() {
		return children;
	}

	public void setChildren(TreeSet<com.qitai.utils.TreeEntity> children) {
		if(children==null)
			this.children.clear();
		else
			this.children = children;
	}
}
