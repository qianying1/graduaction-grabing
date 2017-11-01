//使用$.extend定义jquery扩展函数
;(function($,window,document,undefinded){
	$.extend({
		testFunc:function(params){
			console.log('Hello,'+(name?name:'Doube')+'!');
		},
		testFunc1:function(params){
			alert('hello,here is a function of myself plugins!');
		}
	})
})(jQuery,document,window);
//调用上面的扩展函数
$.testFunc("my name is Sambin");
$.testFunc1("here is so many params");

//使用$.fn开发规范的jquery扩展函数
;(function($,window,document,undefinded){
	//定义Beautifier的构造函数
	var Beautifier=function(ele,opt){
		this.$element=ele,
		//编写默认值参数
		this.defaults={
				'color':'red',
				'fontSize':'12px',
				'textDecoration':'none'
		},
		this.options=$.extend({},this.defaults,opt);	//有传参会以传入的实际参数设置参数，没有传入参数则以默认参数设置；其中{}的作用是用于保护好默认参数，使后面的代码还能够使用该默认参数
	}
	
	//定義Beautifier的方法
	Beautifier.prototype={
			beautiFunc1:function(){
				return this.$element.css({
					'color':this.options.color,
					'fontSize':this.options.fontSize,
					'textDecoration':this.options.textDecoration
				});
			},
			beautiFunc2:function(params){
				alert("here is func2 jquery extend plugin test!");
			}
	};
	
	//在插件中使用Beautifier對象
	//使用Beautifier實現插件方法一
	$.fn.myPlugin=function(params){
		//創建Beautifier實體對象
		var beautifier=new Beautifier(this,options);
		//調用其方法
		return beautifier.beatiFunc1();
	},
	//使用Beautifier實現插件方法二
	$.fn.myPlugin=function(params){
		//創建Beautifier實體對象
		var beautifier=new Beautifier(this,options);
		//調用其方法
		return beautifier.beatiFunc2(params);
	}
})(jQuery,window,document);