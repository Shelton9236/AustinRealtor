<!DOCTYPE html>

<html lang="en-US">
<@common.header/>

<body class="page-sub-page page-submit" id="page-top">
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
                <li><a href="#">Home</a></li>
                <li class="active">Add Realtor</li>
            </ol>
        </div>
        <!-- end Breadcrumb -->

        <div class="container">
            <header><h1>Add Realtor</h1></header>
            <form role="form" id="form-submit" class="form-submit" method="post" enctype="multipart/form-data"
                  action="/house/add">
                <div class="row">
                    <div class="block">
                        <div class="col-md-9 col-sm-9">
                            <section id="submit-form">
                                <section id="basic-information">
                                    <header><h2>Basic Information</h2></header>
                                    <div class="row">
                                        <div class="col-md-8">
                                            <div class="form-group">
                                                <label for="submit-title">Title</label>
                                                <input type="text" class="form-control" id="submit-title" name="name"
                                                       required>
                                            </div><!-- /.form-group -->
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label for="submit-price">Price</label>
                                                <div class="input-group">
                                                    <input type="text" class="form-control" id="submit-price"
                                                           name="price" pattern="\d*" required>
                                                    <span class="input-group-addon">USD</span>
                                                </div>
                                            </div><!-- /.form-group -->
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="submit-description">Remarks</label>
                                        <textarea class="form-control" id="submit-description" rows="8" name="remarks"
                                                  required></textarea>
                                    </div><!-- /.form-group -->
                                </section><!-- /#basic-information -->

                                <section>
                                    <div class="row">
                                        <div class="block clearfix">
                                            <div class="col-md-6 col-sm-6">
                                                <section id="summary">
                                                    <header><h2>Details</h2></header>
                                                    <div class="form-group">
                                                        <label for="submit-location">City</label>
                                                        <select name="cityId" id="submit-location">
                                                            <option value="0">Choose City</option>
                                                          <#list citys as city >
                                                            <option value="${city.id}">${city.cityName}</option>
                                                          </#list>
                                                        </select>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="submit-location">Community</label>
                                                        <select name="communityId" id="submit-location">
                                                            <option value="0">Choose ZipCode</option>
                                                          <#list communitys as community >
                                                            <option value="${community.id}">${community.name}</option>
                                                          </#list>
                                                        </select>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-6 col-sm-6">
                                                            <div class="form-group">
                                                                <label for="submit-Beds">Bedroom</label>
                                                                <input type="text" class="form-control" id="submit-Beds"
                                                                       name="beds" pattern="\d*" required>
                                                            </div><!-- /.form-group -->
                                                        </div><!-- /.col-md-6 -->
                                                        <div class="col-md-6 col-sm-6">
                                                            <div class="form-group">
                                                                <label for="submit-status">State</label>
                                                                <select name="type" id="submit-status">
                                                                    <option value="1">Sale</option>
                                                                    <option value="2">Rent</option>
                                                                </select>
                                                            </div><!-- /.form-group -->
                                                        </div><!-- /.col-md-6 -->
                                                    </div><!-- /.row -->
                                                    <div class="row">
                                                        <div class="col-md-6 col-sm-6">
                                                            <div class="form-group">
                                                                <label for="submit-area">Space</label>
                                                                <div class="input-group">
                                                                    <input type="text" class="form-control"
                                                                           id="submit-area" name="area" pattern="\d*"
                                                                           required>
                                                                    <span class="input-group-addon">m<sup>2</sup></span>
                                                                </div>
                                                            </div><!-- /.form-group -->
                                                        </div><!-- /.col-md-6 -->
                                                        <div class="col-md-6 col-sm-6">
                                                            <div class="form-group">
                                                                <label for="submit-Baths">Bathroom</label>
                                                                <input type="text" class="form-control"
                                                                       id="submit-Baths" name="baths" pattern="\d*"
                                                                       required>
                                                            </div><!-- /.form-group -->
                                                        </div><!-- /.col-md-6 -->
                                                    </div><!-- /.row -->
                                                </section><!-- /#summary -->
                                            </div><!-- /.col-md-6 -->
                                            <div class="col-md-6 col-sm-6">
                                                <section id="place-on-map">
                                                    <header class="section-title">
                                                        <h2>Address</h2>
                                                    </header>
                                                    <div class="form-group">
                                                        <label for="address-map">Address</label>
                                                        <input type="text" class="form-control" id="address-map"
                                                               name="address">
                                                    </div><!-- /.form-group -->

                                                </section>
                                            </div>
                                        </div>
                                    </div>
                                </section>

                                <section class="block" id="gallery">
                                    <header><h2>Images</h2></header>
                                    <div class="center">
                                        <div class="form-group">
                                            <input id="file-upload" type="file" class="file" multiple="true"
                                                   data-show-upload="false" data-show-caption="false"
                                                   data-show-remove="false" accept="image/jpeg,image/png"
                                                   data-browse-class="btn btn-default" data-browse-label="Browse Images"
                                                   name="houseFiles">
                                            <figure class="note"> You can upload all images at once!
                                            </figure>
                                        </div>
                                    </div>
                                </section>

                                <section class="block" id="gallery">
                                    <header><h2>Floor Plan</h2></header>
                                    <div class="center">
                                        <div class="form-group">
                                            <input id="file-upload" type="file" class="file" multiple="true"
                                                   data-show-upload="false" data-show-caption="false"
                                                   data-show-remove="false" accept="image/jpeg,image/png"
                                                   data-browse-class="btn btn-default" data-browse-label="Browse Images"
                                                   name="floorPlanFiles">
                                            <figure class="note"> You can upload all images at once!
                                            </figure>
                                        </div>
                                    </div>
                                </section>

                                <section id="property-features" class="block">
                                    <section>
                                        <header><h2>Properties</h2></header>
                                        <ul class="submit-features">
                                            <li>
                                                <div class="checkbox"><label><input name="featureList" value="New Construciton"
                                                                                    type="checkbox">New construciton</label></div>
                                            </li>
                                            <li>
                                                <div class="checkbox"><label><input name="featureList" value="Within 5 Years Old"
                                                                                    type="checkbox">Within 5 Years old</label></div>
                                            </li>
                                            <li>
                                                <div class="checkbox"><label><input name="featureList" value="Long hour sunshine"
                                                                                    type="checkbox">Long hour sunshine</label></div>
                                            </li>
                                            <li>
                                                <div class="checkbox"><label><input name="featureList" value="Safe neighborhood"
                                                                                    type="checkbox">Safe neighborhood</label></div>
                                            </li>
                                            <li>
                                                <div class="checkbox"><label><input name="featureList" value="Multi storeys"
                                                                                    type="checkbox">Multi storeys</label></div>
                                            </li>
                                            <li>
                                                <div class="checkbox"><label><input name="featureList" value="Park available"
                                                                                    type="checkbox">Park available</label></div>
                                            </li>
                                            <li>
                                                <div class="checkbox"><label><input name="featureList" value="Reasonable price"
                                                                                    type="checkbox">Reasonable pric</label></div>
                                            </li>
                                            <li>
                                                <div class="checkbox"><label><input name="featureList" value="More than 10 years"
                                                                                    type="checkbox">More than 10 years</label></div>
                                            </li>
                                            <li>
                                                <div class="checkbox"><label><input name="featureList" value="Balcony available"
                                                                                    type="checkbox">Balcony available</label></div>
                                            </li>
                                            <li>
                                                <div class="checkbox"><label><input name="featureList" value="Low tax"
                                                                                    type="checkbox">Low tax</label></div>
                                            </li>
                                            <li>
                                                <div class="checkbox"><label><input name="featureList" value="Subway station"
                                                                                    type="checkbox">Subway Station</label></div>
                                            </li>
                                            <li>
                                                <div class="checkbox"><label><input name="featureList" value="Bus station"
                                                                                    type="checkbox">Bus route</label></div>
                                            </li>
                                            <li>
                                                <div class="checkbox"><label><input name="featureList" value="2+ Schools"
                                                                                    type="checkbox">2+ Schools</label></div>
                                            </li>
                                        </ul>
                                    </section>
                                </section>
                                <hr>
                            </section>
                        </div><!-- /.col-md-9 -->
<#--                        <div class="col-md-3 col-sm-3">-->
<#--                            <aside class="submit-step">-->
<#--                                <figure class="step-number">2</figure>-->
<#--                                <div class="description">-->
<#--                                    <h4>Enter Information About Property</h4>-->
<#--                                    <p>Type information about your property. Be descriptive.-->
<#--                                    </p>-->
<#--                                </div>-->
<#--                            </aside><!-- /.submit-step &ndash;&gt;-->
<#--                        </div><!-- /.col-md-3 &ndash;&gt;-->
                    </div>
                </div><!-- /.row -->
                <div class="row">
                    <div class="block">
                        <div class="col-md-9 col-sm-9">
                            <div class="center">
                                <div class="form-group">
                                    <button type="submit" class="btn btn-default large">Add</button>
                                </div><!-- /.form-group -->
                            </div>
                        </div>
                    </div>
                </div>
            </form><!-- /#form-submit -->
        </div><!-- /.container -->
    </div>
    <!-- end Page Content -->
    <!-- Page Footer -->
     <@common.footer/>
    <!-- end Page Footer -->
</div>

<script type="text/javascript"
        src="http://maps.google.com/maps/api/js?key=AIzaSyABT1kCnk8CW4Ckpd0RisUg25PIdDD3Gfg"></script>

<@common.js/>
<!--[if gt IE 8]>
<script type="text/javascript" src="/static/assets/js/ie.js"></script>
<![endif]-->
<#--<script type="text/javascript">-->


<#--    $(document).ready(function () {-->
<#--        var errorMsg = "${errorMsg!""}";-->
<#--        var successMsg = "${successMsg!""}";-->
<#--        if (errorMsg) {-->
<#--            errormsg("error", errorMsg);-->
<#--        }-->
<#--        if (successMsg) {-->
<#--            successmsg("success", successMsg);-->
<#--        }-->
<#--    })-->


<#--    var _latitude = 39.99;-->
<#--    var _longitude = 116.46;-->

<#--    google.maps.event.addDomListener(window, 'load', initSubmitMap(_latitude, _longitude));-->

<#--    function initSubmitMap(_latitude, _longitude) {-->
<#--        var mapCenter = new google.maps.LatLng(_latitude, _longitude);-->
<#--        var mapOptions = {-->
<#--            zoom: 15,-->
<#--            center: mapCenter,-->
<#--            disableDefaultUI: false,-->
<#--            //scrollwheel: false,-->
<#--            styles: mapStyles-->
<#--        };-->
<#--        var mapElement = document.getElementById('submit-map');-->
<#--        var map = new google.maps.Map(mapElement, mapOptions);-->
<#--        var marker = new MarkerWithLabel({-->
<#--            position: mapCenter,-->
<#--            map: map,-->
<#--            icon: '/static/assets/img/marker.png',-->
<#--            labelAnchor: new google.maps.Point(50, 0),-->
<#--            draggable: true-->
<#--        });-->
<#--        $('#submit-map').removeClass('fade-map');-->
<#--        google.maps.event.addListener(marker, "mouseup", function (event) {-->
<#--            var latitude = this.position.lat();-->
<#--            var longitude = this.position.lng();-->
<#--            $('#latitude').val(this.position.lat());-->
<#--            $('#longitude').val(this.position.lng());-->
<#--        });-->

<#--//      Autocomplete-->
<#--        // var input = /** @type {HTMLInputElement} */( document.getElementById('address-map') );-->
<#--        // var autocomplete = new google.maps.places.Autocomplete(input);-->
<#--        // autocomplete.bindTo('bounds', map);-->
<#--        // google.maps.event.addListener(autocomplete, 'place_changed', function() {-->
<#--        //     var place = autocomplete.getPlace();-->
<#--        //     if (!place.geometry) {-->
<#--        //         return;-->
<#--        //     }-->
<#--        //     if (place.geometry.viewport) {-->
<#--        //         map.fitBounds(place.geometry.viewport);-->
<#--        //     } else {-->
<#--        //         map.setCenter(place.geometry.location);-->
<#--        //         map.setZoom(17);-->
<#--        //     }-->
<#--        //     marker.setPosition(place.geometry.location);-->
<#--        //     marker.setVisible(true);-->
<#--        //     $('#latitude').val( marker.getPosition().lat() );-->
<#--        //     $('#longitude').val( marker.getPosition().lng() );-->
<#--        //     var address = '';-->
<#--        //     if (place.address_components) {-->
<#--        //         address = [-->
<#--        //             (place.address_components[0] && place.address_components[0].short_name || ''),-->
<#--        //             (place.address_components[1] && place.address_components[1].short_name || ''),-->
<#--        //             (place.address_components[2] && place.address_components[2].short_name || '')-->
<#--        //         ].join(' ');-->
<#--        //     }-->
<#--        // });-->

<#--    }-->

<#--    function success(position) {-->
<#--        initSubmitMap(position.coords.latitude, position.coords.longitude);-->
<#--        $('#latitude').val(position.coords.latitude);-->
<#--        $('#longitude').val(position.coords.longitude);-->
<#--    }-->

<#--    $('.geo-location').on("click", function () {-->
<#--        if (navigator.geolocation) {-->
<#--            $('#submit-map').addClass('fade-map');-->
<#--            navigator.geolocation.getCurrentPosition(success);-->
<#--        } else {-->
<#--            error('Geo Location is not supported');-->
<#--        }-->
<#--    });-->


<#--</script>-->

</body>
</html>