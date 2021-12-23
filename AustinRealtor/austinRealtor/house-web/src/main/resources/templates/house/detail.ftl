<!DOCTYPE html>

<html lang="en-US">
<@common.header/>

<body class="page-sub-page page-property-detail" id="page-top">
<!-- Wrapper -->
<div class="wrapper">
    <!-- Navigation -->
    <@common.nav/><!-- /.navigation -->
    <!-- end Navigation -->
    <!-- Page Content -->
    <div id="page-content">
        <!-- Breadcrumb -->
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="/">Home</a></li>
                <li class="active">Details</li>
            </ol>
        </div>
        <!-- end Breadcrumb -->

        <div class="container">
            <div class="row">
                <!-- Property Detail Content -->
                <div class="col-md-9 col-sm-9">
                    <section id="property-detail">
                        <header class="property-title">
                            <h1>${house.name}</h1>
                            <figure>${house.address}</figure>

                        <#if loginUser??>
                            <span class="actions">
                                <!--<a href="#" class="fa fa-print"></a>-->
                                <a href="#" class="bookmark" data-bookmark-state="empty"
 
                                ><span class="title-add">Add to bookmark</span><span class="title-added">Added</span></a>
                            </span>
                       </#if>
                        </header>
                        <section id="property-gallery">
                            <div class="owl-carousel property-carousel">
                                <#list house.imageList as image> 
                                   <div class="property-slide">
                                      <a href="${image}" class="image-popup">
                                         <div class="overlay"><h3>Front View</h3></div>
                                         <img alt="" src="${image}">
                                     </a>
                                   </div><!-- /.property-slide -->
                                </#list>
                            </div><!-- /.property-carousel -->
                        </section>
                        <div class="row">
                            <div class="col-md-4 col-sm-12">
                                <section id="quick-summary" class="clearfix">
                                    <header><h2>Information</h2></header>
                                    <dl>
                                        <dt>Address</dt>
                                            <dd>${house.address}</dd>
                                        <dt>Price</dt>
                                            <dd><span class="tag price">$${house.priceStr}</span></dd>
                                        <dt>Type</dt>
<#--                                            <dd>Sale</dd>-->
                                            <dd>${house.remarks}</dd>
                                        <dt>Space</dt>
                                            <dd>${house.area} m<sup>2</sup></dd>
                                        <dt>Bedroom</dt>
                                            <dd>${house.beds}</dd>
                                        <dt>Bathroom</dt>
                                            <dd>${house.baths}</dd>
                                        <dt>Rating</dt>
                                            <dd><div class="rating rating-overall" data-score="${house.rating}"></div></dd>
                                    </dl>
                                </section><!-- /#quick-summary -->
                            </div><!-- /.col-md-4 -->
                            <div class="col-md-8 col-sm-12">
<#--                                <section id="description">-->
<#--                                    <header><h2>Type</h2></header>-->
<#--                                    ${house.remarks}-->
<#--                                </section>-->
                                <!-- /#description -->
                                <section id="property-features">
                                    <header><h2>Description</h2></header>
                                    <ul class="list-unstyled property-features-list">
                                    <#list house.featureList as feature> 
                                        <li>${feature}</li>
                                    </#list>
                                    </ul>
                                </section><!-- /#property-features -->
<#--                                <section id="floor-plans">-->
<#--                                    <div class="floor-plans">-->
<#--                                        <header><h2>Floor Plan</h2></header>-->
<#--                                         <#list house.floorPlanList as floorPlan> -->
<#--                                            <a href="${floorPlan}" class="image-popup"><img alt="" src="${floorPlan}"></a>-->
<#--                                         </#list>-->
<#--                                    </div>-->
<#--                                </section>-->
                                <!-- /#floor-plans -->
                               <!--   <section id="property-map">
                                    <header><h2>Map</h2></header>
                                    <div class="property-detail-map-wrapper">
                                        <div class="property-detail-map" id="property-detail-map"></div>
                                    </div>
                                </section> -->
                                <section id="property-rating">
                                    <header><h2>Rating</h2></header>
                                    <div class="clearfix">
                                        <aside>
                                            <header>Your Rating</header>
                                            <div class="rating rating-user">
                                                <div class="inner"></div>
                                            </div>
                                        </aside>
                                        <figure>
                                            <header>Overall Rating</header>
                                            <div class="rating rating-overall" data-score="${house.rating}"></div>
                                        </figure>
                                    </div>
                                    <div class="rating-form">
                                    </div><!-- /.rating-form -->
                                </section><!-- /#property-rating -->
                                

                                
                            </div><!-- /.col-md-8 -->
                           
                            <div class="col-md-12 col-sm-12">
                               
                                <hr class="thick">
                                <section id="comments">
                                    <header><h2 class="no-border">Comments</h2></header>
                                    <ul class="comments">
                                      <#list commentList as comment> 
                                        <li class="comment" style="width: 830px;">
                                            <figure>
                                                <div class="image">
                                                    <img alt="" src="${comment.avatar}">
                                                </div>
                                            </figure>
                                            <div class="comment-wrapper">
                                                <div class="name pull-left">${comment.userName}</div>
                                                <span class="date pull-right"><span class="fa fa-calendar"></span>${(comment.createTime)?date}</span>
                                                <p>${comment.content}
                                                </p>
                                                <hr>
                                            </div>
                                        </li>
                                      </#list>
                                    </ul>
                                    <div class="agent-form">
                                        <form role="form" id="form-contact-agent" method="post" action="/comment/leaveComment" class="clearfix">
                                            <input type="hidden" name="houseId" value="${house.id}">
                                            <div class="form-group">
                                                <label for="form-contact-agent-message">Review</label>
                                                <textarea class="form-control" id="form-contact-agent-message" rows="2" name="content" required></textarea>
                                            </div><!-- /.form-group -->
                                            <div class="form-group">
                                                <button type="submit" class="btn pull-right btn-default" id="form-contact-agent-submit">Comment</button>
                                            </div><!-- /.form-group -->
                                            <div id="form-contact-agent-status"></div>
                                        </form><!-- /#form-contact -->
                                    </div>
                                </section>
                            </div><!-- /.col-md-12 -->
                            
                        </div><!-- /.row -->
                    </section><!-- /#property-detail -->
                </div><!-- /.col-md-9 -->
                <!-- end Property Detail Content -->

                <!-- sidebar -->
                <div class="col-md-3 col-sm-3">
                    <section id="sidebar">
                        <aside id="edit-search">
                            <header><h3>Search</h3></header>
                            <form role="form" id="searchForm" class="form-search" method="post" action="/house/list">

                                <div class="form-group">
                                    <input type="text" class="form-control" id="search-box-property-id" value="${(vo.name)!}" name="name" placeholder="City or Agent Name">
                                </div>
                                <div class="form-group">
                                    <select name="type">
                                        <option value="1" >Type</option>
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
                                    <div class="tag price">￥${(house.price)!}</div>
                                </div>
                            </div><!-- /.property -->
                            </#list>
                        </aside><!-- /#featured-properties -->
                       
                    </section><!-- /#sidebar -->
                </div><!-- /.col-md-3 -->
                <!-- end Sidebar -->
            </div><!-- /.row -->
        </div><!-- /.container -->
    </div>
    <!-- end Page Content -->
    <!-- Page Footer -->
    <@common.footer/>
    <!-- end Page Footer -->
</div>

<@common.js/>

<!--[if gt IE 8]>
<script type="text/javascript" src="/static//js/ie.js"></script>
<![endif]-->
 <script  type="text/javascript" >
     
    
    
    $(window).load(function(){
        initializeOwl(false);
    });


     $(document).ready(function() {
          var errorMsg   = "${errorMsg!""}";
          var successMsg = "${successMsg!""}";
          if(errorMsg){ 
              errormsg("error",errorMsg);
          }
          if(successMsg) {
              successmsg("success",successMsg);
          }

           var ratingUser = $('.rating-user');
            if (ratingUser.length > 0) {
                $('.rating-user .inner').raty({
                    path: '/static/assets/img',
                    starOff : 'big-star-off.png',
                    starOn  : 'big-star-on.png',
                    width: 150,
                    //target : '#hint',
                    targetType : 'number',
                    targetFormat : 'Rating: {score}',
                    click: function(score, evt) {
                        showRatingForm();
                        $.ajax({
                               url: "/house/rating?id=${house.id}&rating="+score,
                               type: 'GET',
                               cache:false,
                               timeout:60000
                              })
                              .done(function(ret) {
                                   
                              })
                    }
                });
            }
        })

    var bookmarkButton = $(".bookmark");

    
    bookmarkButton.on("click", function() {
        if (bookmarkButton.data('bookmark-state') == 'empty') {
            commonAjax('/house/bookmark?id=${house.id}');
        } else if (bookmarkButton.data('bookmark-state') == 'added') {
            commonAjax('/house/unbookmark?id=${house.id}');
        }
    });
        
 </script>

</body>
</html>