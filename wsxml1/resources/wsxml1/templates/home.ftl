<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feeds</title>
    <style>
        main {
            display: grid;
            grid-template-columns: 70% auto;
            column-gap: 10px;
        }
        .rss_item { border-bottom: 2px dotted #666666; margin-bottom: 8px; }
        .rss_item h3 { font-size: 20px; margin: 0 0 5px 0; }
        .rss_item .description { color: #888888; font-style: italic; }
        .rss_item .read { margin: 3px 0 0 3px; text-align: right;}
        .rss_item .read a { 
            display: inline-block;
            text-decoration: none;
            background: violet;
            color: white;
            padding: 10px 16px;
            border-radius: 5px;
         }
    </style>
</head>
<body>
    <main>
    
        <section></section>
        <aside>
            <h2>Srednja vrednost - RSD</h2>
            <table>
                <tr>
                    <td><b>Valuta</b></td>
                    <td><b>Vrednost u RSD</b></td>
                </tr>
                <#list kursna_lista as item>
                <tr>
                    <td>${item.valuta}</td>
                    <td>${item.vrednost}</td>
                </tr>
                </#list>
            </table>

            <h2>TechCrunch Gadgets - Latest 5 articles</h2>
            <#list items as item>
            <div class="rss_item">
                <h3>${item.title}</h3>
                <div class="description">
                ${item.description}
                </div>
                <p class="read"><a href="${item.link}" target="_blank">Read</a></p>
            </div>
            </#list>
            <p><a href="https://techcrunch.com/gadgets/feed/">RSS Feed</a></p>
        </aside>
    </main>
</body>
</html>