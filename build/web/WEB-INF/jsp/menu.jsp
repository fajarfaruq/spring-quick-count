<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>.:: PILGUB JATIM 2013 KABUPATEN NGANJUK ::.</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reset.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/invalid.css" type="text/css" media="screen" />	

        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/simpla.jquery.configuration.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/facebox.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.wysiwyg.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/modernizr.js"></script>
        <style>
            .no-js #loader { display: none;  }
            .js #loader {	font-family: tahoma, Arial, Verdana, sans-serif !important;
                          font-size:12px;
                          width: 100%; 
                          margin-bottom:0px; 
                          padding:3px 0; 
                          margin: 0; 
                          background:#ffffff;
                          height:100%;
                          position:fixed;
                          top:0px;
                          left:0px;
                          z-index: 99999;
                          padding: 5px;
                          _position:absolute;
                          _top:expression(eval(document.body.scrollTop));
                          -moz-box-shadow: 0px 0px 10px #4f4f4f;
                          -webkit-box-shadow: 0px 0px 10px #4f4f4f;
                          box-shadow: 0px 0px 5px #4f4f4f;
            }

        </style>	
        <script>
            // Wait for window load
            $(window).load(function() {
                $('#loader').fadeOut(100);
            });

        </script>	
    </head>
    <body>
        <div id="loader">Loading, Silahkan tunggu ...</div>
        <div id="header-atas">
            <a href="#"><img id="logo" src="${pageContext.request.contextPath}/resources/images/logo_administrator.png" alt="Simpla Admin logo" /></a>
            <div id="profile-links">
                Selamat Datang, <a href="#" title="Edit profil">${user}</a>
                <a href="${pageContext.request.contextPath}/keluar" title="Sign Out">Keluar</a>
            </div>     

        </div>
        <div id="sidebar"><div id="sidebar-wrapper">
                <ul id="main-nav">
                    <li>
                        <a href="${pageContext.request.contextPath}/" onclick="document.location.reload();" class="nav-top-item no-submenu ${menu0}">
                            Dashboard
                        </a>       
                    </li>

                    <li>
                        <a href="${pageContext.request.contextPath}/kecamatan" class="nav-top-item no-submenu ${menu3}">
                            Kecamatan
                        </a>
                        <ul>
                            <li><a class="${menu4}" href="${pageContext.request.contextPath}/kecamatan/managedata">Manajemen Data</a></li>
                        </ul>
                    </li>

                    <li>
                        <a href="${pageContext.request.contextPath}/kelurahan" class="nav-top-item no-submenu ${menu5}">
                            Kelurahan
                        </a>
                        <ul>
                            <li><a class="${menu6}" href="${pageContext.request.contextPath}/kelurahan/managedata">Manajemen Data</a></li>
                        </ul>
                    </li>

                    <li>
                        <a href="${pageContext.request.contextPath}/tps" class="nav-top-item no-submenu ${menu7}">
                            TPS
                        </a>
                        <ul>
                            <li><a class="${menu8}" href="${pageContext.request.contextPath}/tps/managedata">Manajemen Data</a></li>
                        </ul>
                    </li>

                    <li> 
                        <a href="${pageContext.request.contextPath}/pasangancalon" class="nav-top-item no-submenu  ${menu1}">
                            Pasangan Calon
                        </a>
                        <ul>
                            <li><a class="${menu2}" href="${pageContext.request.contextPath}/pasangancalon/managedata">Manajemen Data</a></li>
                        </ul>
                    </li>

                    <li>
                        <a href="${pageContext.request.contextPath}/perolehansuara" class="nav-top-item no-submenu ${menu9}">
                            Hasil Real Count
                        </a>
                        <ul>
                            <li><a class="${menu10}" href="${pageContext.request.contextPath}/perolehansuara/tps">Detail Suara TPS</a></li>  
                            <li><a class="${menu11}" href="${pageContext.request.contextPath}/perolehansuara/kelurahan">Detail Suara Kelurahan</a></li>      
                            <li><a class="${menu12}" href="${pageContext.request.contextPath}/perolehansuara/kecamatan">Detail Suara Kecamatan</a></li>                                              
                            <li><a class="${menu13}" href="${pageContext.request.contextPath}/perolehansuara/semua">Hasil Akhir</a></li>                            
                        </ul>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/rekapitulasi" class="nav-top-item no-submenu ${menu14}">
                            Hasil Rekapitulasi
                        </a>
                        <ul>
                            <li><a class="${menu15}" href="${pageContext.request.contextPath}/rekapitulasi/tps">Rekapitulasi TPS</a></li>  
                            <li><a class="${menu16}" href="${pageContext.request.contextPath}/rekapitulasi/kelurahan">Rekapitulasi Kelurahan</a></li>  
                            <li><a class="${menu17}" href="${pageContext.request.contextPath}/rekapitulasi/kecamatan">Rekapitulasi Kecamatan</a></li>      
                            <li><a class="${menu18}" href="${pageContext.request.contextPath}/rekapitulasi/semua">Hasil Akhir</a></li>      
                        </ul>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/user" class="nav-top-item no-submenu ${menu19}">
                            User
                        </a>
                        <ul>
                            <li><a class="${menu20}" href="${pageContext.request.contextPath}/user/userkecamatan">User Kecamatan</a></li>                          
                            <li><a class="${menu21}" href="${pageContext.request.contextPath}/user/userkelurahan">User Kelurahan</a></li>
                            <li><a class="${menu22}" href="${pageContext.request.contextPath}/user/usertps">User TPS</a></li> 
                            <li><a class="${menu23}" href="${pageContext.request.contextPath}/user/userhelpdesk">Help Desk</a></li>                            
                            <li><a class="${menu24}" href="${pageContext.request.contextPath}/user/useradmin">User Admin</a></li>                               
                        </ul>
                    </li>

                    <li>
                        <a href="${pageContext.request.contextPath}/pengaturan" class="nav-top-item no-submenu ${menu25}">
                            Pengaturan
                        </a>
                        <ul>
                            <li><a class="${menu26}" href="${pageContext.request.contextPath}/pengaturan/akun">Profil Akun</a></li>
                            <!--<li><a class="${menu22}" href="${pageContext.request.contextPath}/pengaturan/database">Database</a></li> -->
                        </ul>
                    </li>                            
                </ul>
            </div>
        </div>
        <div id="main-content1">

            <noscript>
            <div class="error-serius">
            <div class="notification error png_bg">
                <div>
                    Javascript is disabled or is not supported by your browser. Please <a href="http://browsehappy.com/" title="Upgrade to a better browser">upgrade</a> your browser or <a href="http://www.google.com/support/bin/answer.py?answer=23852" title="Enable Javascript in your browser">enable</a> Javascript to navigate the interface properly.
                </div>
            </div>
            </div>
            </noscript>
            <jsp:include flush="true" page="${kontenutama}">
                <jsp:param name="abc" value="xyz" />
            </jsp:include>

            <div id="footer">
                <small>
                    &#169; Copyright 2013 Pilgub Jatim Kabupaten Nganjuk | Powered by <a target="_blank" href="https://www.facebook.com/fajarfaruq.maulana">F2m</a>
                </small>
            </div>

        </div>
    </body>

</html>
