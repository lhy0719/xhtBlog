<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>异常订单监控系统</title>

    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="<@spring.url ''/>/ui/ace/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="<@spring.url ''/>/ui/ace/assets/css/font-awesome.min.css" />

    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="<@spring.url ''/>/js/pace/themes/blue/pace-theme-center-radar.css"></link>
    <!-- text fonts -->
    <link rel="stylesheet" href="<@spring.url ''/>/ui/ace/assets/css/ace-fonts.css" />

    <!-- ace styles -->
    <link rel="stylesheet" href="<@spring.url ''/>/ui/ace/assets/css/ace.min.css" id="main-ace-style" />

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="<@spring.url ''/>/ui/ace/assets/css/ace-part2.min.css" />
    <![endif]-->
    <link rel="stylesheet" href="<@spring.url ''/>/ui/ace/assets/css/ace-skins.min.css" />
    <link rel="stylesheet" href="<@spring.url ''/>/ui/ace/assets/css/ace-rtl.min.css" />

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="<@spring.url ''/>/ui/ace/assets/css/ace-ie.min.css" />
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->
    <script src="<@spring.url ''/>/ui/ace/assets/js/ace-extra.min.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="<@spring.url ''/>/ui/ace/assets/js/html5shiv.min.js"></script>
    <script src="<@spring.url ''/>/ui/ace/assets/js/respond.min.js"></script>
    <![endif]-->
</head>
<body  class="no-skin">
<!--导航条-->
<div id="navbar" class="navbar navbar-default">
    <script type="text/javascript">
        try{ace.settings.check('navbar' , 'fixed')}catch(e){}
    </script>

    <div class="navbar-container" id="navbar-container">
        <!-- #手机端访问时显示下拉 -->
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler">
            <span class="sr-only">Toggle sidebar</span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>
        </button>

        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small>
                    <i class="fa fa-leaf"></i>
                    订单异常监控系统
                </small>
            </a>
        </div>

        <!-- #导航条的下拉工具条 -->
        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <!-- #section:basics/navbar.user_menu -->
                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="<@spring.url ''/>/ui/ace/assets/avatars/user.jpg" alt="Jason's Photo" />
								<span class="user-info">
									<small>Welcome,</small>
									Jason
								</span>

                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>

                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="#">
                                <i class="ace-icon fa fa-cog"></i>
                                Settings
                            </a>
                        </li>

                        <li>
                            <a href="profile.html">
                                <i class="ace-icon fa fa-user"></i>
                                Profile
                            </a>
                        </li>

                        <li class="divider"></li>

                        <li>
                            <a href="#">
                                <i class="ace-icon fa fa-power-off"></i>
                                Logout
                            </a>
                        </li>
                    </ul>
                </li>

                <!-- /section:basics/navbar.user_menu -->
            </ul>
        </div>

        <!-- /section:basics/navbar.dropdown -->
    </div><!-- /.navbar-container -->
</div>
<!--主容器-->
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <!-- #左边导航栏开始 -->
    <div id="sidebar" class="sidebar responsive">
        <script type="text/javascript">
            try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
        </script>
        <!--导航栏开始的四个图标-->
        <div class="sidebar-shortcuts" id="sidebar-shortcuts">
            <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                <button class="btn btn-success">
                    <i class="ace-icon fa fa-tachometer"></i>
                </button>

                <button class="btn btn-info">
                    <i class="ace-icon fa fa-desktop"></i>
                </button>

                <button class="btn btn-warning">
                    <i class="ace-icon fa fa-list"></i>
                </button>

                <button class="btn btn-danger">
                    <i class="ace-icon fa fa-cogs"></i>
                </button>

            </div>

            <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                <span class="btn btn-success"></span>

                <span class="btn btn-info"></span>

                <span class="btn btn-warning"></span>

                <span class="btn btn-danger"></span>
            </div>
        </div><!-- /.sidebar-shortcuts -->
        <!--导航栏页面列表-->
        <ul class="nav nav-list">
            <li class="active">
                <a href="<@spring.url ''/>/user/home">
                    <i class="menu-icon fa fa-tachometer"></i>
                    <span class="menu-text"> 首页 </span>
                </a>

                <b class="arrow"></b>
            </li>

            <li class="">
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-desktop"></i>
                    <span class="menu-text"> 功能一 </span>

                    <b class="arrow fa fa-angle-down"></b>
                </a>

                <b class="arrow"></b>

                <ul class="submenu">
                    <li class="">
                        <a href="<@spring.url ''/>/user/jdgrid">
                            <i class="menu-icon fa fa-caret-right"></i>
                            列表
                        </a>
                        <b class="arrow"></b>
                    </li>
                    <li class="">
                        <a href="<@spring.url ''/>/user/echarts">
                            <i class="menu-icon fa fa-caret-right"></i>
                            图表一
                        </a>
                        <b class="arrow"></b>
                    </li>
                    <li class="">
                        <a href="<@spring.url ''/>/user/echarts1">
                            <i class="menu-icon fa fa-caret-right"></i>
                            图表二
                        </a>
                        <b class="arrow"></b>
                    </li>
                    <li class="">
                        <a href="<@spring.url ''/>/user/echarts2">
                            <i class="menu-icon fa fa-caret-right"></i>
                            图表三
                        </a>
                        <b class="arrow"></b>
                    </li>
                </ul>
            </li>

            <li class="">
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-list"></i>
                    <span class="menu-text"> 功能二 </span>

                    <b class="arrow fa fa-angle-down"></b>
                </a>

                <b class="arrow"></b>

                <ul class="submenu">
                    <li class="">
                        <a href="tables.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            功能二页面一
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="jqgrid.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            功能二页面一
                        </a>

                        <b class="arrow"></b>
                    </li>
                </ul>
            </li>

        </ul><!-- /.nav-list -->

        <!-- #导航栏收缩按钮 -->
        <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
            <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
        </div>

        <!-- /section:basics/sidebar.layout.minimize -->
        <script type="text/javascript">
            try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
        </script>
    </div>

    <!-- 左侧导航栏结束 -->
    <!-- 右侧主体栏开始 -->
    <div class="main-content">
        <!-- #主体栏头部面包屑导航 -->
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">首页</a>
                </li>
            </ul><!-- /.breadcrumb -->

            <!-- #section:basics/content.searchbox -->
            <div class="nav-search" id="nav-search">
                <form class="form-search">
							<span class="input-icon">
								<input type="text" placeholder="搜索 ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
								<i class="ace-icon fa fa-search nav-search-icon"></i>
							</span>
                </form>
            </div><!-- /.nav-search -->

            <!-- /section:basics/content.searchbox -->
        </div>

        <!-- /主体容器 -->
        <div class="page-content">

            <!-- /页面内容 -->
            <div class="page-content-area">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="alert alert-block alert-success">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon fa fa-times"></i>
                            </button>

                            <i class="ace-icon fa fa-check green"></i>

                            欢迎使用
                            <strong class="green">
                                异常订单监控系统
                                <small>(v0.01)</small>
                            </strong>,
                            演示版
                        </div>
                        <div class="row">
                            <div class="space-6"></div>

                            <div class="col-sm-7 infobox-container">
                                <div class="infobox infobox-green  ">
                                    <div class="infobox-icon">
                                        <i class="icon-comments"></i>
                                    </div>

                                    <div class="infobox-data">
                                        <span class="infobox-data-number">32</span>
                                        <div class="infobox-content">2个异常</div>
                                    </div>
                                    <div class="stat stat-success">8%</div>
                                </div>

                                <div class="infobox infobox-blue  ">
                                    <div class="infobox-icon">
                                        <i class="icon-twitter"></i>
                                    </div>

                                    <div class="infobox-data">
                                        <span class="infobox-data-number">11</span>
                                        <div class="infobox-content">新系统</div>
                                    </div>

                                    <div class="badge badge-success">
                                        +32%
                                        <i class="icon-arrow-up"></i>
                                    </div>
                                </div>

                                <div class="infobox infobox-pink  ">
                                    <div class="infobox-icon">
                                        <i class="icon-shopping-cart"></i>
                                    </div>

                                    <div class="infobox-data">
                                        <span class="infobox-data-number">8</span>
                                        <div class="infobox-content">新订单</div>
                                    </div>
                                    <div class="stat stat-important">4%</div>
                                </div>

                                <div class="infobox infobox-red  ">
                                    <div class="infobox-icon">
                                        <i class="icon-beaker"></i>
                                    </div>

                                    <div class="infobox-data">
                                        <span class="infobox-data-number">7</span>
                                        <div class="infobox-content">调查</div>
                                    </div>
                                </div>

                                <div class="infobox infobox-orange2  ">
                                    <div class="infobox-chart">
                                        <span class="sparkline" data-values="196,128,202,177,154,94,100,170,224"></span>
                                    </div>

                                    <div class="infobox-data">
                                        <span class="infobox-data-number">6,251</span>
                                        <div class="infobox-content">页面查看次数</div>
                                    </div>

                                    <div class="badge badge-success">
                                        7.2%
                                        <i class="icon-arrow-up"></i>
                                    </div>
                                </div>

                                <div class="infobox infobox-blue2  ">
                                    <div class="infobox-progress">
                                        <div class="easy-pie-chart percentage" data-percent="42" data-size="46">
                                            <span class="percent">42</span>%
                                        </div>
                                    </div>

                                    <div class="infobox-data">
                                        <span class="infobox-text">系统使用</span>

                                        <div class="infobox-content">
                                            <span class="bigger-110">~</span>
                                            剩余58GB
                                        </div>
                                    </div>
                                </div>

                                <div class="space-6"></div>

                                <div class="infobox infobox-green infobox-small infobox-dark">
                                    <div class="infobox-progress">
                                        <div class="easy-pie-chart percentage" data-percent="61" data-size="39">
                                            <span class="percent">61</span>%
                                        </div>
                                    </div>

                                    <div class="infobox-data">
                                        <div class="infobox-content">任务</div>
                                        <div class="infobox-content">完成</div>
                                    </div>
                                </div>

                                <div class="infobox infobox-blue infobox-small infobox-dark">
                                    <div class="infobox-chart">
                                        <span class="sparkline" data-values="3,4,2,3,4,4,2,2"></span>
                                    </div>

                                    <div class="infobox-data">
                                        <div class="infobox-content">获得</div>
                                        <div class="infobox-content">$32,000</div>
                                    </div>
                                </div>

                                <div class="infobox infobox-grey infobox-small infobox-dark">
                                    <div class="infobox-icon">
                                        <i class="icon-download-alt"></i>
                                    </div>

                                    <div class="infobox-data">
                                        <div class="infobox-content">下载次数</div>
                                        <div class="infobox-content">1,205</div>
                                    </div>
                                </div>
                            </div>

                            <div class="vspace-sm"></div>

                            <div class="col-sm-5">
                                <div class="widget-box">
                                    <div class="widget-header widget-header-flat widget-header-small">
                                        <h5>
                                            <i class="icon-signal"></i>
                                            访问来源
                                        </h5>

                                        <div class="widget-toolbar no-border">
                                            <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown">
                                                本周
                                                <i class="icon-angle-down icon-on-right bigger-110"></i>
                                            </button>

                                            <ul class="dropdown-menu pull-right dropdown-125 dropdown-lighter dropdown-caret">
                                                <li class="active">
                                                    <a href="#" class="blue">
                                                        <i class="icon-caret-right bigger-110">&nbsp;</i>
                                                        本周
                                                    </a>
                                                </li>

                                                <li>
                                                    <a href="#">
                                                        <i class="icon-caret-right bigger-110 invisible">&nbsp;</i>
                                                        上周
                                                    </a>
                                                </li>

                                                <li>
                                                    <a href="#">
                                                        <i class="icon-caret-right bigger-110 invisible">&nbsp;</i>
                                                        本月
                                                    </a>
                                                </li>

                                                <li>
                                                    <a href="#">
                                                        <i class="icon-caret-right bigger-110 invisible">&nbsp;</i>
                                                        上月
                                                    </a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>

                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <div id="piechart-placeholder"></div>

                                            <div class="hr hr8 hr-double"></div>

                                            <div class="clearfix">
                                                <div class="grid3">
															<span class="grey">
																<i class="icon-facebook-sign icon-2x blue"></i>
																&nbsp; likes
															</span>
                                                    <h4 class="bigger pull-right">1,255</h4>
                                                </div>

                                                <div class="grid3">
															<span class="grey">
																<i class="icon-twitter-sign icon-2x purple"></i>
																&nbsp; tweets
															</span>
                                                    <h4 class="bigger pull-right">941</h4>
                                                </div>

                                                <div class="grid3">
															<span class="grey">
																<i class="icon-pinterest-sign icon-2x red"></i>
																&nbsp; pins
															</span>
                                                    <h4 class="bigger pull-right">1,050</h4>
                                                </div>
                                            </div>
                                        </div><!-- /widget-main -->
                                    </div><!-- /widget-body -->
                                </div><!-- /widget-box -->
                            </div><!-- /span -->
                        </div><!-- /row -->
                    </div>
                </div>
            </div><!-- /.page-content-area -->
        </div><!-- /.page-content -->
    </div>
    <!--页脚-->
    <div class="footer">
        <div class="footer-inner">
            <!-- #section:basics/footer -->
            <div class="footer-content">
						<span class="bigger-120">
							<span class="blue bolder">JD</span>
							OFC &copy; 2016
						</span>

                &nbsp; &nbsp;
						<span class="action-buttons">
							<a href="#">
                                <i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
                            </a>

							<a href="#">
                                <i class="ace-icon fa fa-facebook-square text-primary bigger-150"></i>
                            </a>

							<a href="#">
                                <i class="ace-icon fa fa-rss-square orange bigger-150"></i>
                            </a>
						</span>
            </div>

            <!-- /section:basics/footer -->
        </div>
    </div>

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div>
<!-- basic scripts -->

<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='<@spring.url ''/>/ui/ace/assets/js/jquery.min.js'>"+"<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='<@spring.url ''/>/ui/ace/assets/js/jquery1x.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='<@spring.url ''/>/ui/ace/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="<@spring.url ''/>/ui/ace/assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->
<script src="<@spring.url ''/>/ui/ace/assets/js/jquery-ui.custom.min.js"></script>
<script src="<@spring.url ''/>/ui/ace/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="<@spring.url ''/>/ui/ace/assets/js/jquery.easypiechart.min.js"></script>
<script src="<@spring.url ''/>/ui/ace/assets/js/jquery.sparkline.min.js"></script>
<script src="<@spring.url ''/>/ui/ace/assets/js/flot/jquery.flot.min.js"></script>
<script src="<@spring.url ''/>/ui/ace/assets/js/flot/jquery.flot.pie.min.js"></script>
<script src="<@spring.url ''/>/ui/ace/assets/js/flot/jquery.flot.resize.min.js"></script>
<script type="text/javascript" src="<@spring.url ''/>/js/pace/pace.min.js"></script>
<!--[if lte IE 8]>
<script src="<@spring.url ''/>/ui/ace/assets/js/excanvas.min.js"></script>
<![endif]-->
<!-- ace scripts -->
<script src="<@spring.url ''/>/ui/ace/assets/js/ace-elements.min.js"></script>
<script src="<@spring.url ''/>/ui/ace/assets/js/ace.min.js"></script>
<!-- inline scripts related to this page -->
<!-- inline scripts related to this page -->
<script type="text/javascript">
    jQuery(function($) {
        $('.easy-pie-chart.percentage').each(function(){
            var $box = $(this).closest('.infobox');
            var barColor = $(this).data('color') || (!$box.hasClass('infobox-dark') ? $box.css('color') : 'rgba(255,255,255,0.95)');
            var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)' : '#E2E2E2';
            var size = parseInt($(this).data('size')) || 50;
            $(this).easyPieChart({
                barColor: barColor,
                trackColor: trackColor,
                scaleColor: false,
                lineCap: 'butt',
                lineWidth: parseInt(size/10),
                animate: /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase()) ? false : 1000,
                size: size
            });
        })

        $('.sparkline').each(function(){
            var $box = $(this).closest('.infobox');
            var barColor = !$box.hasClass('infobox-dark') ? $box.css('color') : '#FFF';
            $(this).sparkline('html',
                    {
                        tagValuesAttribute:'data-values',
                        type: 'bar',
                        barColor: barColor ,
                        chartRangeMin:$(this).data('min') || 0
                    });
        });


        //flot chart resize plugin, somehow manipulates default browser resize event to optimize it!
        //but sometimes it brings up errors with normal resize event handlers
        $.resize.throttleWindow = false;

        var placeholder = $('#piechart-placeholder').css({'width':'90%' , 'min-height':'150px'});
        var data = [
            { label: "social networks",  data: 38.7, color: "#68BC31"},
            { label: "search engines",  data: 24.5, color: "#2091CF"},
            { label: "ad campaigns",  data: 8.2, color: "#AF4E96"},
            { label: "direct traffic",  data: 18.6, color: "#DA5430"},
            { label: "other",  data: 10, color: "#FEE074"}
        ]
        function drawPieChart(placeholder, data, position) {
            $.plot(placeholder, data, {
                series: {
                    pie: {
                        show: true,
                        tilt:0.8,
                        highlight: {
                            opacity: 0.25
                        },
                        stroke: {
                            color: '#fff',
                            width: 2
                        },
                        startAngle: 2
                    }
                },
                legend: {
                    show: true,
                    position: position || "ne",
                    labelBoxBorderColor: null,
                    margin:[-30,15]
                }
                ,
                grid: {
                    hoverable: true,
                    clickable: true
                }
            })
        }
        drawPieChart(placeholder, data);

        /**
         we saved the drawing function and the data to redraw with different position later when switching to RTL mode dynamically
         so that's not needed actually.
         */
        placeholder.data('chart', data);
        placeholder.data('draw', drawPieChart);


        //pie chart tooltip example
        var $tooltip = $("<div class='tooltip top in'><div class='tooltip-inner'></div></div>").hide().appendTo('body');
        var previousPoint = null;

        placeholder.on('plothover', function (event, pos, item) {
            if(item) {
                if (previousPoint != item.seriesIndex) {
                    previousPoint = item.seriesIndex;
                    var tip = item.series['label'] + " : " + item.series['percent']+'%';
                    $tooltip.show().children(0).text(tip);
                }
                $tooltip.css({top:pos.pageY + 10, left:pos.pageX + 10});
            } else {
                $tooltip.hide();
                previousPoint = null;
            }

        });






        var d1 = [];
        for (var i = 0; i < Math.PI * 2; i += 0.5) {
            d1.push([i, Math.sin(i)]);
        }

        var d2 = [];
        for (var i = 0; i < Math.PI * 2; i += 0.5) {
            d2.push([i, Math.cos(i)]);
        }

        var d3 = [];
        for (var i = 0; i < Math.PI * 2; i += 0.2) {
            d3.push([i, Math.tan(i)]);
        }


        var sales_charts = $('#sales-charts').css({'width':'100%' , 'height':'220px'});
        $.plot("#sales-charts", [
            { label: "Domains", data: d1 },
            { label: "Hosting", data: d2 },
            { label: "Services", data: d3 }
        ], {
            hoverable: true,
            shadowSize: 0,
            series: {
                lines: { show: true },
                points: { show: true }
            },
            xaxis: {
                tickLength: 0
            },
            yaxis: {
                ticks: 10,
                min: -2,
                max: 2,
                tickDecimals: 3
            },
            grid: {
                backgroundColor: { colors: [ "#fff", "#fff" ] },
                borderWidth: 1,
                borderColor:'#555'
            }
        });


        $('#recent-box [data-rel="tooltip"]').tooltip({placement: tooltip_placement});
        function tooltip_placement(context, source) {
            var $source = $(source);
            var $parent = $source.closest('.tab-content')
            var off1 = $parent.offset();
            var w1 = $parent.width();

            var off2 = $source.offset();
            //var w2 = $source.width();

            if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
            return 'left';
        }


        $('.dialogs,.comments').ace_scroll({
            size: 300
        });


        //Android's default browser somehow is confused when tapping on label which will lead to dragging the task
        //so disable dragging when clicking on label
        var agent = navigator.userAgent.toLowerCase();
        if("ontouchstart" in document && /applewebkit/.test(agent) && /android/.test(agent))
            $('#tasks').on('touchstart', function(e){
                var li = $(e.target).closest('#tasks li');
                if(li.length == 0)return;
                var label = li.find('label.inline').get(0);
                if(label == e.target || $.contains(label, e.target)) e.stopImmediatePropagation() ;
            });

        $('#tasks').sortable({
                    opacity:0.8,
                    revert:true,
                    forceHelperSize:true,
                    placeholder: 'draggable-placeholder',
                    forcePlaceholderSize:true,
                    tolerance:'pointer',
                    stop: function( event, ui ) {
                        //just for Chrome!!!! so that dropdowns on items don't appear below other items after being moved
                        $(ui.item).css('z-index', 'auto');
                    }
                }
        );
        $('#tasks').disableSelection();
        $('#tasks input:checkbox').removeAttr('checked').on('click', function(){
            if(this.checked) $(this).closest('li').addClass('selected');
            else $(this).closest('li').removeClass('selected');
        });


        //show the dropdowns on top or bottom depending on window height and menu position
        $('#task-tab .dropdown-hover').on('mouseenter', function(e) {
            var offset = $(this).offset();

            var $w = $(window)
            if (offset.top > $w.scrollTop() + $w.innerHeight() - 100)
                $(this).addClass('dropup');
            else $(this).removeClass('dropup');
        });

    })
</script>
</body>
</html>