/* 图片播放 */
$(function(){
	var xxxban = function () {
		var list,
			imgul = $('.banner_0_top'),
			bonul = $('.banner_0_bottom'),
			numx = 0,
			maxnum = $('.banner_0_top li').size() - 1,
			speed=4;
		imgul.find('li:gt(0)').css({opacity : 0});
		imgul.find('li:eq(0)').css({zIndex : 5});
		bonul.find('span').eq(0).addClass('on');
		imgul.find('li').eq(0).find('.tit').css({'bottom' : imgul.find('li').eq(0).find('.p').outerHeight()});
		bonul.find('span').each(function(e){
			$(this).click(function(){
				if (e == numx) {
					return false;
				} else {
					bonul.find('span').removeClass('on');
					bonul.find('span').eq(e).addClass('on');
					imgul.find('li').eq(numx).animate({opacity : 0, zIndex : 0});
					imgul.find('li').eq(e).animate({opacity:1, zIndex:maxnum});	
					imgul.find('li').eq(e).find('.tit').css({'bottom' : imgul.find('li').eq(e).find('.p').outerHeight()});
					numx = e;
				}
			});
		});
		//前翻
		$('.banner_0 .next').unbind( 'click' );
		$('.banner_0 .next').click(function(){
			var xx = numx - 1;
			if (xx < 0) {xx = maxnum;}
			bonul.find('span').eq(xx).click();
		});
		//后翻
		$('.banner_0 .por').unbind('click');
		$('.banner_0 .por').click(function () {
			var xx = numx + 1;
			if (xx > maxnum) {xx = 0;}
			bonul.find('span').eq(xx).click();
		});

		var xx = function () {
			var nimei = numx + 1;
			if (nimei > maxnum) {nimei = 0;}
			bonul.find('span').eq(nimei).click();
		};
	
		var interv = setInterval(xx, speed * 1000);

		bonul.hover(function () {
				clearInterval(interv);
			}, function () {
			interv = setInterval(xx, speed * 1000);
		});
		
		$('.banner_0 .por,.banner_0 .next').hover(function () {
			clearInterval(interv);
			}, function () {
				interv = setInterval(xx, speed * 1000);
		});
	};
	xxxban();
});


/* tab页 */
(function ($) {
	$(function () {
		$('.tab_tou').tabHover({content : $('.tab_tou_container')});
	});

	$.fn.tabHover = function (options) {
		var $this = $(this),
			$content = options.content;
		$this.mouseenter(function () {
			var $index = $(this).attr('data-index'),
				$cc = $content.eq($index);
			// 显示当前信息
			$content.css('display', 'none');
			$cc.css('display', 'block');
			$this.removeClass('active');
			$(this).addClass('active');
		});
	};
})(jQuery);

function hasPlaceholderSupport() {
	return 'placeholder' in document.createElement('input');
}

var PlaceHolder = {
	_support: (function() {
		return 'placeholder' in document.createElement('input');
	})(),

    //提示文字的样式，需要在页面中其他位置定义
	init: function() {
		if (!PlaceHolder._support) {
			//未对textarea处理，需要的自己加上
			var inputs = document.getElementsByTagName('input');
			PlaceHolder.create(inputs);
		}
	},

	create: function(inputs) {
		var input;
		if (!inputs.length) {
			inputs = [inputs];
		}
		for (var i = 0, length = inputs.length; i <length; i++) {
			input = inputs[i];
			if (!PlaceHolder._support && input.attributes && input.attributes.placeholder) {
				PlaceHolder._setValue(input);
				input.onfocus = function(e) {
					if (this.value === this.attributes.placeholder.nodeValue) {
						this.value = '';
					}
				};

				input.onblur =  function(e) {
					if (this.value === '') {
						PlaceHolder._setValue(this);
					}
				};
			}
		}
	},

	_setValue: function(input) {
		input.value = input.attributes.placeholder.nodeValue;
	}
};

/* 图片播放 */
(function($) {
	$(function() {
		var jcarousel = $('.jcarousel');
		var imgWidth = 0;
		jcarousel
			.on('jcarousel:reload jcarousel:create', function () {
				var width = jcarousel.innerWidth();

				if (width >= 600) {
					width = width / 1;
					imgWidth = width / 8.5;
				} else if (width >= 350) {
					width = width / 4;
				}

				jcarousel.jcarousel('items').css('width', width + 'px');
			})
			.jcarousel({
				wrap: 'circular'
			});

		$('.jcarousel-control-prev')
			.jcarouselControl({
				target: '-=1'
			});

		$('.jcarousel-control-next')
			.jcarouselControl({
				target: '+=1'
			});

		$('.jcarousel-pagination')
			.on('jcarouselpagination:active', 'a', function() {
				$(this).addClass('active');
			})
			.on('jcarouselpagination:inactive', 'a', function() {
				$(this).removeClass('active');
			})
			.on('click', function(e) {
				e.preventDefault();
			})
			.jcarouselPagination({
				perPage: 1,
				item: function(page) {
					return '<a href="#' + page + '">' + page + '</a>';
				}
			});
	});
})(jQuery);

/* 办件公示 */
$(function() {
	$(".newsticker-jcarousellite").jCarouselLite({
		vertical: true,
		hoverPause:true,
		visible: 3,
		auto:500,
		speed:3000
	});
});

$(document).ready(function () {
	PlaceHolder.init();
	$('#login_a').mouseenter(function () {
		$('#login_box').css('display', 'block');
	}).click(function () {
		$('#login_box').css('display', 'none');
	});
});

