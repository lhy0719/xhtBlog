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
    <link rel="stylesheet" href="<@spring.url ''/>/js/pace/themes/blue/pace-theme-center-circle.css"></link>
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
                    <li class="active">
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
                <li class="active">列表</li>
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
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="widget-box widget-color-blue ui-sortable-handle">
                            <div class="widget-header">
                                <h5>查询条件</h5>

                            </div>
                            <div class="widget-body">
                                <div class="widget-main">
                                <form class="form-horizontal" role="form">
                                    <!-- #section:elements.form -->
                                    <div class="row">
                                        <div class="col-xs-5">
                                            <div class="form-group">
                                                <label class="col-xs-2 control-label no-padding-right"
                                                       for="form-field-1"> 订单号 </label>

                                                <div class="col-xs-10">
                                                    <input class="form-control" type="text" id="form-field-1"
                                                           placeholder="输入订单号"/>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-xs-2 control-label no-padding-right"
                                                       for="form-field-2"> 错误码 </label>

                                                <div class="col-xs-10">
                                                    <input class="form-control" type="text" id="form-field-2"
                                                           placeholder="输入错误码"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-1">

                                        </div>
                                        <div class="col-xs-5">
                                            <div class="form-group">
                                                <label class="col-xs-2 control-label no-padding-right"
                                                       for="form-field-4"> 系统名 </label>

                                                <div class="col-xs-10">
                                                    <input class="form-control" type="text" id="form-field-4"
                                                           placeholder="输入系统名"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-xs-2 control-label no-padding-right"> 创建时间 </label>

                                                <div class="col-xs-10">
                                                    <div class="input-daterange input-group " style="width: 100%;">
                                                        <input type="text" class="input-sm form-control" name="start"/>
                                                <span class="input-group-addon">
                                                    <i class="fa fa-exchange"></i>
                                                </span>

                                                        <input type="text" class="input-sm form-control" name="end"/>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <table id="grid-table"></table>

                        <div id="grid-pager"></div>

                        <!-- PAGE CONTENT ENDS -->
                    </div>
                    <!-- /.col -->
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
<script src="<@spring.url ''/>/ui/ace/assets/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="<@spring.url ''/>/ui/ace/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="<@spring.url ''/>/ui/ace/assets/js/jqGrid/i18n/grid.locale-en.js"></script>
<script src="<@spring.url ''/>/ui/ace/assets/js/date-time/moment.min.js"></script>
<script src="<@spring.url ''/>/ui/ace/assets/js/date-time/daterangepicker.min.js"></script>
<script type="text/javascript" src="<@spring.url ''/>/js/pace/pace.min.js"></script>
<!-- ace scripts -->
<script src="<@spring.url ''/>/ui/ace/assets/js/ace-elements.min.js"></script>
<script src="<@spring.url ''/>/ui/ace/assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->
<script type="text/javascript">
    var grid_data =
            [
                {id: "1", name: "Desktop Computer", note: "note", stock: "Yes", ship: "FedEx", sdate: "2007-12-03"},
                {id: "2", name: "Laptop", note: "Long text ", stock: "Yes", ship: "InTime", sdate: "2007-12-03"},
                {id: "3", name: "LCD Monitor", note: "note3", stock: "Yes", ship: "TNT", sdate: "2007-12-03"},
                {id: "4", name: "Speakers", note: "note", stock: "No", ship: "ARAMEX", sdate: "2007-12-03"},
                {id: "5", name: "Laser Printer", note: "note2", stock: "Yes", ship: "FedEx", sdate: "2007-12-03"},
                {id: "6", name: "Play Station", note: "note3", stock: "No", ship: "FedEx", sdate: "2007-12-03"},
                {
                    id: "7",
                    name: "Mobile Telephone",
                    note: "note",
                    stock: "Yes",
                    ship: "ARAMEX",
                    sdate: "2007-12-03"
                },
                {id: "8", name: "Server", note: "note2", stock: "Yes", ship: "TNT", sdate: "2007-12-03"},
                {id: "9", name: "Matrix Printer", note: "note3", stock: "No", ship: "FedEx", sdate: "2007-12-03"},
                {
                    id: "10",
                    name: "Desktop Computer",
                    note: "note",
                    stock: "Yes",
                    ship: "FedEx",
                    sdate: "2007-12-03"
                },
                {id: "11", name: "Laptop", note: "Long text ", stock: "Yes", ship: "InTime", sdate: "2007-12-03"},
                {id: "12", name: "LCD Monitor", note: "note3", stock: "Yes", ship: "TNT", sdate: "2007-12-03"},
                {id: "13", name: "Speakers", note: "note", stock: "No", ship: "ARAMEX", sdate: "2007-12-03"},
                {id: "14", name: "Laser Printer", note: "note2", stock: "Yes", ship: "FedEx", sdate: "2007-12-03"},
                {id: "15", name: "Play Station", note: "note3", stock: "No", ship: "FedEx", sdate: "2007-12-03"},
                {
                    id: "16",
                    name: "Mobile Telephone",
                    note: "note",
                    stock: "Yes",
                    ship: "ARAMEX",
                    sdate: "2007-12-03"
                },
                {id: "17", name: "Server", note: "note2", stock: "Yes", ship: "TNT", sdate: "2007-12-03"},
                {id: "18", name: "Matrix Printer", note: "note3", stock: "No", ship: "FedEx", sdate: "2007-12-03"},
                {id: "19", name: "Matrix Printer", note: "note3", stock: "No", ship: "FedEx", sdate: "2007-12-03"},
                {
                    id: "20",
                    name: "Desktop Computer",
                    note: "note",
                    stock: "Yes",
                    ship: "FedEx",
                    sdate: "2007-12-03"
                },
                {id: "21", name: "Laptop", note: "Long text ", stock: "Yes", ship: "InTime", sdate: "2007-12-03"},
                {id: "22", name: "LCD Monitor", note: "note3", stock: "Yes", ship: "TNT", sdate: "2007-12-03"},
                {id: "23", name: "Speakers", note: "note", stock: "No", ship: "ARAMEX", sdate: "2007-12-03"}
            ];
    jQuery(function ($) {
        var grid_selector = "#grid-table";
        var pager_selector = "#grid-pager";

//        //resize to fit page size
//        $(window).on('resize.jqGrid', function () {
//            $(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
//        });

        jQuery(grid_selector).jqGrid({
            //direction: "rtl",
            subGrid: false,
            data: grid_data,
            datatype: "local",
            height: 270,
            colNames: [' ', '订单号', '创建时间', '系统名', '错误码', '异常类型', '异常描述'],
            colModel: [
                {
                    name: 'myac', index: '', width: 80, fixed: true, sortable: false, resize: false,
                    formatter: 'actions',
                    formatoptions: {
                        keys: true,
                        //delbutton: false,//disable delete button

                        delOptions: {recreateForm: true, beforeShowForm: beforeDeleteCallback},
                        //editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}
                    }
                },
                {name: 'id', index: 'id', width: 60, sorttype: "int", editable: true},
                {name: 'sdate', index: 'sdate', width: 90, editable: true, sorttype: "date", unformat: pickDate},
                {
                    name: 'name',
                    index: 'name',
                    width: 150,
                    editable: true,
                    editoptions: {size: "20", maxlength: "30"}
                },
                {
                    name: 'stock',
                    index: 'stock',
                    width: 70,
                    editable: true,
                    edittype: "checkbox",
                    editoptions: {value: "Yes:No"},
                    unformat: aceSwitch
                },
                {
                    name: 'ship',
                    index: 'ship',
                    width: 90,
                    editable: true,
                    edittype: "select",
                    editoptions: {value: "FE:FedEx;IN:InTime;TN:TNT;AR:ARAMEX"}
                },
                {
                    name: 'note',
                    index: 'note',
                    width: 150,
                    sortable: false,
                    editable: true,
                    edittype: "textarea",
                    editoptions: {rows: "2", cols: "10"}
                }
            ],

            viewrecords: true,
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: pager_selector,
            altRows: true,
            //toppager: true,

            multiselect: true,
            //multikey: "ctrlKey",
            multiboxonly: true,

            loadComplete: function () {
                var table = this;
                setTimeout(function () {
                    styleCheckbox(table);

                    updateActionIcons(table);
                    updatePagerIcons(table);
                    enableTooltips(table);
                }, 0);
            },

            editurl: "/dummy.html",//nothing is saved
            caption: "监控信息列表",

            autowidth: true


            /**
             ,
             grouping:true,
             groupingView : {
						 groupField : ['name'],
						 groupDataSorted : true,
						 plusicon : 'fa fa-chevron-down bigger-110',
						 minusicon : 'fa fa-chevron-up bigger-110'
					},
             caption: "Grouping"
             */

        });
        $(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size


        //enable search/filter toolbar
        //jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})
        //jQuery(grid_selector).filterToolbar({});


        //switch element when editing inline
        function aceSwitch(cellvalue, options, cell) {
            setTimeout(function () {
                $(cell).find('input[type=checkbox]')
                        .addClass('ace ace-switch ace-switch-5')
                        .after('<span class="lbl"></span>');
            }, 0);
        }

        //enable datepicker
        function pickDate(cellvalue, options, cell) {
            setTimeout(function () {
                $(cell).find('input[type=text]')
                        .datepicker({format: 'yyyy-mm-dd', autoclose: true});
            }, 0);
        }


        //navButtons
        jQuery(grid_selector).jqGrid('navGrid', pager_selector,
                { 	//navbar options
                    edit: false,
                    editicon: 'ace-icon fa fa-pencil blue',
                    add: false,
                    addicon: 'ace-icon fa fa-plus-circle purple',
                    del: false,
                    delicon: 'ace-icon fa fa-trash-o red',
                    search: false,
                    searchicon: 'ace-icon fa fa-search orange',
                    refresh: false,
                    refreshicon: 'ace-icon fa fa-refresh green',
                    view: false,
                    viewicon: 'ace-icon fa fa-search-plus grey',
                },
                {
                    //edit record form
                    //closeAfterEdit: true,
                    //width: 700,
                    recreateForm: false,
                    beforeShowForm: function (e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
                        style_edit_form(form);
                    }
                },
                {
                    //new record form
                    //width: 700,
                    closeAfterAdd: true,
                    recreateForm: true,
                    viewPagerButtons: false,
                    beforeShowForm: function (e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
                                .wrapInner('<div class="widget-header" />');
                        style_edit_form(form);
                    }
                },
                {
                    //delete record form
                    recreateForm: true,
                    beforeShowForm: function (e) {
                        var form = $(e[0]);
                        if (form.data('styled')) return false;

                        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
                        style_delete_form(form);

                        form.data('styled', true);
                    },
                    onClick: function (e) {
                        alert(1);
                    }
                },
                {
                    //search form
                    recreateForm: true,
                    afterShowSearch: function (e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />');
                        style_search_form(form);
                    },
                    afterRedraw: function () {
                        style_search_filters($(this));
                    }
                    ,
                    multipleSearch: true,
                    /**
                     multipleGroup:true,
                     showQuery: true
                     */
                },
                {
                    //view record form
                    recreateForm: true,
                    beforeShowForm: function (e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                    }
                }
        );


        function style_edit_form(form) {
            //enable datepicker on "sdate" field and switches for "stock" field
            form.find('input[name=sdate]').datepicker({format: 'yyyy-mm-dd', autoclose: true})
                    .end().find('input[name=stock]')
                    .addClass('ace ace-switch ace-switch-5').after('<span class="lbl"></span>');
            //don't wrap inside a label element, the checkbox value won't be submitted (POST'ed)
            //.addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');

            //update buttons classes
            var buttons = form.next().find('.EditButton .fm-button');
            buttons.addClass('btn btn-sm').find('[class*="-icon"]').hide();//ui-icon, s-icon
            buttons.eq(0).addClass('btn-primary').prepend('<i class="ace-icon fa fa-check"></i>');
            buttons.eq(1).prepend('<i class="ace-icon fa fa-times"></i>');

            buttons = form.next().find('.navButton a');
            buttons.find('.ui-icon').hide();
            buttons.eq(0).append('<i class="ace-icon fa fa-chevron-left"></i>');
            buttons.eq(1).append('<i class="ace-icon fa fa-chevron-right"></i>');
        }

        function style_delete_form(form) {
            var buttons = form.next().find('.EditButton .fm-button');
            buttons.addClass('btn btn-sm btn-white btn-round').find('[class*="-icon"]').hide();//ui-icon, s-icon
            buttons.eq(0).addClass('btn-danger').prepend('<i class="ace-icon fa fa-trash-o"></i>');
            buttons.eq(1).addClass('btn-default').prepend('<i class="ace-icon fa fa-times"></i>')
        }

        function style_search_filters(form) {
            form.find('.delete-rule').val('X');
            form.find('.add-rule').addClass('btn btn-xs btn-primary');
            form.find('.add-group').addClass('btn btn-xs btn-success');
            form.find('.delete-group').addClass('btn btn-xs btn-danger');
        }

        function style_search_form(form) {
            var dialog = form.closest('.ui-jqdialog');
            var buttons = dialog.find('.EditTable');
            buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'ace-icon fa fa-retweet');
            buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'ace-icon fa fa-comment-o');
            buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'ace-icon fa fa-search');
        }

        function beforeDeleteCallback(e) {
            var form = $(e[0]);
            if (form.data('styled')) return false;

            form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
            style_delete_form(form);

            form.data('styled', true);
        }

        function beforeEditCallback(e) {
            var form = $(e[0]);
            form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
            style_edit_form(form);
        }


        //it causes some flicker when reloading or navigating grid
        //it may be possible to have some custom formatter to do this as the grid is being created to prevent this
        //or go back to default browser checkbox styles for the grid
        function styleCheckbox(table) {
            /**
             $(table).find('input:checkbox').addClass('ace')
             .wrap('<label />')
             .after('<span class="lbl align-top" />')


             $('.ui-jqgrid-labels th[id*="_cb"]:first-child')
             .find('input.cbox[type=checkbox]').addClass('ace')
             .wrap('<label />').after('<span class="lbl align-top" />');
             */
        }


        //unlike navButtons icons, action icons in rows seem to be hard-coded
        //you can change them like this in here if you want
        function updateActionIcons(table) {
            /**
             var replacement =
             {
                 'ui-ace-icon fa fa-pencil' : 'ace-icon fa fa-pencil blue',
                 'ui-ace-icon fa fa-trash-o' : 'ace-icon fa fa-trash-o red',
                 'ui-icon-disk' : 'ace-icon fa fa-check green',
                 'ui-icon-cancel' : 'ace-icon fa fa-times red'
             };
             $(table).find('.ui-pg-div span.ui-icon').each(function(){
						var icon = $(this);
						var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
						if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
					})
             */
        }

        //replace icons with FontAwesome icons like above
        function updatePagerIcons(table) {
            var replacement =
            {
                'ui-icon-seek-first': 'ace-icon fa fa-angle-double-left bigger-140',
                'ui-icon-seek-prev': 'ace-icon fa fa-angle-left bigger-140',
                'ui-icon-seek-next': 'ace-icon fa fa-angle-right bigger-140',
                'ui-icon-seek-end': 'ace-icon fa fa-angle-double-right bigger-140'
            };
            $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function () {
                var icon = $(this);
                var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

                if ($class in replacement) icon.attr('class', 'ui-icon ' + replacement[$class]);
            })
        }

        function enableTooltips(table) {
            $('.navtable .ui-pg-button').tooltip({container: 'body'});
            $(table).find('.ui-pg-div').tooltip({container: 'body'});
        }

        //var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');

        $(document).on('ajaxloadstart', function (e) {
            $(grid_selector).jqGrid('GridUnload');
            $('.ui-jqdialog').remove();
        });

        $('.input-daterange').datepicker({autoclose: true});
    });
</script>
</body>
</html>