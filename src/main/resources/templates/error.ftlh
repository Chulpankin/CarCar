<#import "/templates/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Error</title>
    <!-- Materialize CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" rel="stylesheet"/>
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet"/>
</head>
<body>

<div class="container">
    <div class="row" style="margin-top: 4rem;">
        <div class="col s12 m8 offset-m2">
            <div class="card red lighten-2">
                <div class="card-content white-text center-align">
                    <i class="fas fa-exclamation-triangle fa-4x"></i>
                    <h4>Error</h4>
                    <p>
                        <#if errorMessage??>
                            ${errorMessage}
                        <#else>
                            Sorry, an unexpected error occurred.
                        </#if>
                    </p>
                    <#if failedUrl??>
                        <p class="grey-text text-lighten-4">
                            <small>Failed URL: ${failedUrl}</small>
                        </p>
                    </#if>
                </div>
                <div class="card-action center-align">
                    <a href="/" class="btn waves-effect waves-light white red-text text-lighten-2">
                        <i class="fas fa-home left"></i>Go to Home
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Materialize JS (для инициализации, если понадобится) -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>
