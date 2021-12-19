<#macro header>
<html lang="en-US">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="ThemeStarz">
<#--    <link rel="Shortcut Icon" href="/static/assets/img/favicon.ico"/>-->

    <link href='http://fonts.googleapis.com/css?family=Roboto:300,400,700' rel='stylesheet' type='text/css'>
    <link href="/static/assets/fonts/font-awesome.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/static/assets/bootstrap/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="/static/assets/css/bootstrap-select.min.css" type="text/css">
    <link rel="stylesheet" href="/static/assets/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="/static/assets/css/jquery.slider.min.css" type="text/css">
    <link rel="stylesheet" href="/static/assets/css/owl.carousel.css" type="text/css">
    <link rel="stylesheet" href="/static/assets/css/style.css" type="text/css">
    <link rel="stylesheet" href="/static/assets/css/owl.transitions.css" type="text/css">
    <link rel="stylesheet" href="/static/assets/css/fileinput.min.css" type="text/css">

    <link rel="stylesheet" href="/static/assets/css/toastr.css" type="text/css">

    <title>Austin Realtor</title>
</head>
</#macro>

<#macro footer>
   <footer id="page-footer">
       <div class="inner">
           <aside id="footer-main">
               <div class="container">
                   <div class="row">

                       <div class="col-md-6 col-sm-6">
                           <article>
                               <h3>About Us</h3>
                               <p>
                                   ECE656 Group 20: Xiaotong Liu, Stella Guo
                               </p>
                               <hr>
                           </article>
                       </div><!-- /.col-sm-3 -->
                       <div class="col-md-3 col-sm-3">
                           <article>
                               <h3>Contacts</h3>
                               <a href="#">x772liu@uwaterloo.ca</a>
                               <a href="#">l64guo@uwaterloo.ca</a>
                           </article>
                       </div><!-- /.col-sm-3 -->
                       <div class="col-md-3 col-sm-3">
                           <article>
                               <h3>Links</h3>
                               <ul class="list-unstyled list-links">
                                   <li><a href="/index">Main Site</a></li>
                                   <li><a href="/accounts/register">Login/Register</a></li>
                               </ul>
                           </article>
                       </div>
                   </div><!-- /.row -->
               </div><!-- /.container -->
           </aside><!-- /#footer-main -->
           <aside id="footer-thumbnails" class="footer-thumbnails"></aside><!-- /#footer-thumbnails -->
           <aside id="footer-copyright">
               <div class="container">
                   <span class="pull-right"><a href="#page-top" class="roll">Go to top</a></span>
               </div>
           </aside>
       </div><!-- /.inner -->
   </footer>
</#macro>

<#macro js>
<script type="text/javascript" src="/static/assets/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="/static/assets/js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="/static/assets/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/assets/js/smoothscroll.js"></script>

<script type="text/javascript" src="/static/assets/js/owl.carousel.min.js"></script>
<script type="text/javascript" src="/static/assets/js/bootstrap-select.min.js"></script>
<script type="text/javascript" src="/static/assets/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="/static/assets/js/jquery.placeholder.js"></script>
<script type="text/javascript" src="/static/assets/js/icheck.min.js"></script>
<script type="text/javascript" src="/static/assets/js/jquery.vanillabox-0.1.5.min.js"></script>
<script type="text/javascript" src="/static/assets/js/retina-1.1.0.min.js"></script>
<script type="text/javascript" src="/static/assets/js/jquery.raty.min.js"></script>
<script type="text/javascript" src="/static/assets/js/jquery.magnific-popup.min.js"></script>
<script type="text/javascript" src="/static/assets/js/jshashtable-2.1_src.js"></script>
<script type="text/javascript" src="/static/assets/js/jquery.numberformatter-1.2.3.js"></script>
<script type="text/javascript" src="/static/assets/js/tmpl.js"></script>
<script type="text/javascript" src="/static/assets/js/jquery.dependClass-0.1.js"></script>
<script type="text/javascript" src="/static/assets/js/draggable-0.1.js"></script>
<script type="text/javascript" src="/static/assets/js/jquery.slider.js"></script>
<script type="text/javascript" src="/static/assets/js/jquery.fitvids.js"></script>
<script type="text/javascript" src="/static/assets/js/fileinput.min.js"></script>
<script type="text/javascript" src="/static/assets/js/custom-map.js"></script>
<script type="text/javascript" src="/static/assets/js/custom.js"></script>
<script type="text/javascript" src="/static/assets/js/toastr.js"></script>
<script type="text/javascript" src="/static/common/common.js"></script>

</#macro>

<#macro hot>
<aside id="featured-properties">
    <header><h3>Hot</h3></header>
    <#list recomHouses as house>
    <div class="property small">
        <a href="/house/detail?id=${house.id}">
            <div class="property-image">
                <img alt="" src="${(house.firstImg)!}" style="width: 100px;height: 75px">
            </div>
        </a>
        <div class="info">
            <a href="/house/detail?id=${house.id}"><h4>${(house.name)!}</h4></a>
            <figure>${(house.address)!} </figure>
            <div class="tag price">$${(house.price)!} </div>
        </div>
    </div><!-- /.property -->
    </#list>
</aside><!-- /#featured-properties -->
</#macro>

<#macro search>
<aside id="edit-search">
    <header><h3>Search Properties</h3></header>
    <form role="form" id="_searchForm" class="form-search" method="post" action="/house/list">

        <div class="form-group">
            <input type="text" class="form-control" id="search-box-property-id" value="${(vo.name)!}" name="name"
                   placeholder="City or Agency">
        </div>
        <div class="form-group">
            <select name="type">
<#--                <option value="1">Type</option>-->
                <option value="1" <#if (vo.type)?? && (vo.type)==1> selected </#if> >Sale</option>
                <option value="2" <#if (vo.type)?? && (vo.type)==2> selected </#if> >Rent</option>
            </select>
        </div><!-- /.form-group -->
        <input type="text" value="${(vo.sort)!}" name=sort hidden="true">

        <div class="form-group">
            <button type="submit" class="btn btn-default">Search</button>
        </div><!-- /.form-group -->
    </form><!-- /#form-map -->
</aside><!-- /#edit-search -->
</#macro>


<#macro nav>
 <div class="navigation">
     <div class="secondary-navigation">
         <div class="container">
             <div class="contact">

             </div>
             <div class="user-area">
                 <div class="actions">
                    <#if (loginUser.name)??>
                        <a href="/accounts/profile" class="promoted">Hello,${(loginUser.name)!}</a>
                          <#if (loginUser.email)?? && (loginUser.email) == "jgcs12345@163.com">
                          <a href="/agency/create" class="promoted">Create Agent Account</a>
                          </#if>
                        <a href="/accounts/logout" class="promoted">Exit</a>
                    <#else>
                        <a href="/accounts/register" class="promoted"><strong>Register</strong></a>
                        <a href="/accounts/signin">Sign In</a>
                    </#if>
                 </div>
                 <div class="language-bar">
                     <a href="/accounts/profile" class="active"><img alt="" style="width:20px;height:20px"
                                                                     src="${(loginUser.avatar)!}"/></a>
                 </div>
             </div>
         </div>
     </div>
     <div class="container">
         <header class="navbar" id="top" role="banner">
             <div class="navbar-header">
                 <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".bs-navbar-collapse">
                     <span class="sr-only">Toggle navigation</span>
                     <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                     <span class="icon-bar"></span>
                 </button>
                 <div class="navbar-brand nav" id="brand">
                     <a href="/"><img src="/static/assets/img/logo.png" style="width:90px;height:60px" alt="brand"></a>
                     <span style="color: Black;font-size: 16px;">AustinRealtor</span>
                 </div>
             </div>
             <nav class="collapse navbar-collapse bs-navbar-collapse navbar-right" role="navigation">
                 <ul class="nav navbar-nav">
                     <li><a href="/index">Main</a></li>
                     <li class="has-child"><a href="/house/list">Sale</a>
                         <ul class="child-navigation">
                             <li><a href="/house/list?type=1">Sale</a></li>
                             <li><a href="/house/list?type=2">Rent</a></li>
                         </ul>
                     </li>
                     <li class="has-child"><a href="#">Agent/Broker </a>
                         <ul class="child-navigation">
                             <li><a href="/agency/agentList">Agent/Broker</a></li>
                             <li><a href="/agency/list">Agency</a></li>
                         </ul>
                     </li>
<#--                     <li><a href="/blog/list">房产百科</a></li>-->
<#--                     <li><a href="https://sz.lianjia.com/ditu/">地图找房</a></li>-->
                 </ul>
             </nav><!-- /.navbar collapse-->
             <div class="add-your-property">
                 <a href="/house/toAdd" class="btn btn-default"><i class="fa fa-plus"></i><span class="text">Add</span></a>
             </div>
         </header><!-- /.navbar -->
     </div><!-- /.container -->
 </div>
</#macro>

<#macro paging pagination>
        <ul class="pagination">
           <#list pagination.pages as page>
               <#if pagination.pageNum==page>
                    <li class="active"><a href="#">${page}</a></li>
               <#else>
                    <li><a href="javascript:void(0)" onclick="nextPage(${page},${pagination.pageSize})">${page}</a></li>
               </#if>
           </#list>
        </ul><!-- /.pagination-->
</#macro>