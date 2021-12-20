package com.qitai.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.TreeSet;

/**
 * @author liucheng
 * @date 2020/2/20 11:49
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeForApplication implements Comparable<com.qitai.utils.TreeForApplication>{
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
		private String moduleId;
		/**
		 * 节点显示文本
		 */
		private String title;

		/**
		 * 节点显示的图标
		 */
		private String icon;

		private String code;

		/*
		 * 父节点标识值
		 */
		private String parentId;

		/**
		 * 排序值
		 */
		private int sort;

	public Integer getsDefault() {
		return sDefault;
	}

	public void setsDefault(Integer sDefault) {
		this.sDefault = sDefault;
	}

	private Integer sDefault = 0;

		/**
		 * 用于内部标识，此节点是否已处理过
		 */
		@JsonIgnore
		private boolean load;

	    /**
	    * 模块打开的url相对地址
	    */
		private String pageUrl;

		/**
		 * 节点自定义属性
		 */
		private Object attributes;

		/**
		 * 子节点集
		 */
		private TreeSet<com.qitai.utils.TreeForApplication> children;

		public TreeForApplication()
		{
			children=new TreeSet<>();
		}

		/**
		 * 构造节点
		 * @param sId
		 * @param sText
		 * @param sParentId
		 * @param iSort
		 * @param sIcon 节点图标样式定义（null表示默认图标，""表示使用空图标）
		 */
		public TreeForApplication(String sId,String sTitle,String sPageUrl,int iCheck,Integer sIcon){
			this();
			moduleId=sId;
			title=sTitle;
			pageUrl=sPageUrl;
			sDefault=iCheck;
		}

		@Override
		public int compareTo(com.qitai.utils.TreeForApplication o)
		{
			if(o==null) return 1;
			if(this==o) return 0;//如果是同一对象，返回0（用于移除）
			//注意：不能返回0，因为在TreeSet排序时，如果比较是0值时，会认为是在同一个位置放置元素（将会覆盖原来的元素）
			if(this.sort==o.sort){//如果排序值相同，再比较标题
				String stext=this.title;
				if(stext==null)stext="";
				int ir=stext.compareTo(o.title==null?"":o.title);
				if(ir==0){
					stext=this.moduleId;
					if(stext==null)stext="";
					ir=stext.compareTo(o.moduleId==null?"":o.moduleId);
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

		public String getModuleId() {
			return moduleId;
		}

		public void setModuleId(String moduleId) {
			this.moduleId = moduleId;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}

		public String getPageUrl() {
			return pageUrl;
		}

		public void setPageUrl(String pageUrl) {
			this.pageUrl = pageUrl;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
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

		public TreeSet<com.qitai.utils.TreeForApplication> getChildren() {
			return children;
		}

		public void setChildren(TreeSet<com.qitai.utils.TreeForApplication> children) {
			if(children==null)
				this.children.clear();
			else
				this.children = children;
		}
	}
