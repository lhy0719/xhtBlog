<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>异常订单监控系统</title>

    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="<@spring.url ''/>/ui/ace/assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<@spring.url ''/>/ui/ace/assets/css/font-awesome.min.css"/>

    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="<@spring.url ''/>/ui/ace/assets/css/jquery-ui.min.css"/>
    <link rel="stylesheet" href="<@spring.url ''/>/ui/ace/assets/css/datepicker.css"/>
    <link rel="stylesheet" href="<@spring.url ''/>/ui/ace/assets/css/ui.jqgrid.css"/>
    <link rel="stylesheet" href="<@spring.url ''/>/ui/ace/assets/css/chosen.css"/>
    <link rel="stylesheet" href="<@spring.url ''/>/ui/ace/assets/css/bootstrap-datetimepicker.css"/>

    <!-- text fonts -->
    <link rel="stylesheet" href="<@spring.url ''/>/ui/ace/assets/css/ace-fonts.css"/>

    <!-- ace styles -->
    <link rel="stylesheet" href="<@spring.url ''/>/ui/ace/assets/css/ace.min.css" id="main-ace-style"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="<@spring.url ''/>/ui/ace/assets/css/ace-part2.min.css"/>
    <![endif]-->
    <link rel="stylesheet" href="<@spring.url ''/>/ui/ace/assets/css/ace-skins.min.css"/>
    <link rel="stylesheet" href="<@spring.url ''/>/ui/ace/assets/css/ace-rtl.min.css"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="<@spring.url ''/>/ui/ace/assets/css/ace-ie.min.css"/>
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
<body class="no-skin">
<!--导航条-->
<div id="navbar" class="navbar navbar-default">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
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
                        <img class="nav-user-photo" src="<@spring.url ''/>/ui/ace/assets/avatars/user.jpg"
                             alt="Jason's Photo"/>
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
    </div>
    <!-- /.navbar-container -->
</div>
<!--主容器-->
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <!-- #左边导航栏开始 -->
    <div id="sidebar" class="sidebar responsive">
        <script type="text/javascript">
            try {
                ace.settings.check('sidebar', 'fixed')
            } catch (e) {
            }
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
            <li class="">
                <a href="<@spring.url ''/>/user/home">
                    <i class="menu-icon fa fa-tachometer"></i>
                    <span class="menu-text"> 首页 </span>
                </a>

                <b class="arrow"></b>
            </li>

            <li class="active open">
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
                    <li class="active">
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
            <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left"
               data-icon2="ace-icon fa fa-angle-double-right"></i>
        </div>

        <!-- /section:basics/sidebar.layout.minimize -->
        <script type="text/javascript">
            try {
                ace.settings.check('sidebar', 'collapsed')
            } catch (e) {
            }
        </script>
    </div>

    <!-- 左侧导航栏结束 -->
    <!-- 右侧主体栏开始 -->
    <div class="main-content">
        <!-- #主体栏头部面包屑导航 -->
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">首页</a>
                </li>
                <li class="active">功能一</li>
                <li class="active">图表三</li>
            </ul>
            <!-- /.breadcrumb -->

            <!-- #section:basics/content.searchbox -->
            <div class="nav-search" id="nav-search">
                <form class="form-search">
							<span class="input-icon">
								<input type="text" placeholder="搜索 ..." class="nav-search-input" id="nav-search-input"
                                       autocomplete="off"/>
								<i class="ace-icon fa fa-search nav-search-icon"></i>
							</span>
                </form>
            </div>
            <!-- /.nav-search -->

            <!-- /section:basics/content.searchbox -->
        </div>

        <!-- /主体容器 -->
        <div class="page-content">

            <!-- /页面内容 -->
            <div class="page-content-area">
                <div class="page-header">
                    <h1>
                        echarts
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            展示
                        </small>
                    </h1>
                </div>
                <!-- /.page-header -->
                <div class="row">
                    <div class="col-xs-6">
                        <div id="mainecharts" style="height:500px"></div>
                    </div>
                    <div class="col-xs-6">
                        <div id="mainecharts1" style="height:500px"></div>
                    </div>
                </div>
            </div>
            <!-- /.page-content-area -->
        </div>
        <!-- /.page-content -->
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
    window.jQuery || document.write("<script src='<@spring.url ''/>/ui/ace/assets/js/jquery.min.js'>" + "<" + "/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='<@spring.url ''/>/ui/ace/assets/js/jquery1x.min.js'>" + "<" + "/script>");
</script>
<![endif]-->
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='<@spring.url ''/>/ui/ace/assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>
<script src="<@spring.url ''/>/ui/ace/assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->
<script type="text/javascript" src="<@spring.url ''/>/js/pace/pace.min.js"></script>
<!-- ace scripts -->
<script src="<@spring.url ''/>/ui/ace/assets/js/ace-elements.min.js"></script>
<script src="<@spring.url ''/>/ui/ace/assets/js/ace.min.js"></script>
<script src="<@spring.url ''/>/js/esl/esl.js"></script>
<!-- inline scripts related to this page -->
<script type="text/javascript">

    var option = {
        title : {
            text: '订单异常监控统计',
            subtext: '纯属虚构',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:['台账错误','中间件错误','配送中心错误','延保错误','其它错误']
        },
        calculable : true,
        series : [
            {
                name:'访问来源',
                type:'pie',
                radius : '55%',
                center: ['50%', 225],
                data:[
                    {value:335, name:'台账错误'},
                    {value:310, name:'中间件错误'},
                    {value:234, name:'配送中心错误'},
                    {value:135, name:'延保错误'},
                    {value:1548, name:'其它错误'}
                ]
            }
        ]
    };

    var option2 = {
        tooltip : {
            trigger: 'axis',
            axisPointer : {
                type: 'shadow'
            }
        },
        legend: {
            data:['台账错误','中间件错误','配送中心错误','延保错误','其它错误']
        },
        toolbox: {
            show : true,
            orient : 'vertical',
            y : 'center',
            feature : {
                mark : {show: true},
                magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : ['周一','周二','周三','周四','周五','周六','周日']
            }
        ],
        yAxis : [
            {
                type : 'value',
                splitArea : {show : true}
            }
        ],
        grid: {
            x2:40
        },
        series : [
            {
                name:'台账错误',
                type:'bar',
                stack: '总量',
                data:[320, 332, 301, 334, 390, 330, 320]
            },
            {
                name:'中间件错误',
                type:'bar',
                stack: '总量',
                data:[120, 132, 101, 134, 90, 230, 210]
            },
            {
                name:'配送中心错误',
                type:'bar',
                stack: '总量',
                data:[220, 182, 191, 234, 290, 330, 310]
            },
            {
                name:'延保错误',
                type:'bar',
                stack: '总量',
                data:[150, 232, 201, 154, 190, 330, 410]
            },
            {
                name:'其它错误',
                type:'bar',
                stack: '总量',
                data:[820, 932, 901, 934, 1290, 1330, 1320]
            }
        ]
    };

    require.config({
        packages:[{
            name:'echarts',
            location:'<@spring.url ""/>/js/echarts/src',
            main:'echarts'
        },{
            name:'zrender',
            location:'<@spring.url ""/>/js/zrender/src',
            main:'zrender'
        }]
    });
    require(
            [
                'echarts',
                'echarts/chart/bar',
                'echarts/chart/line',
                'echarts/chart/pie'
            ],
            function(ec) {
                myChart = ec.init(document.getElementById('mainecharts'));
                myChart.setOption(option);
                myChart2 = ec.init(document.getElementById('mainecharts1'));
                myChart2.setOption(option2);

                myChart.connect(myChart2);
                myChart2.connect(myChart);

                setTimeout(function (){
                    window.onresize = function () {
                        myChart.resize();
                        myChart2.resize();
                    }
                },200)
            }
    )
</script>
</body>
</html>