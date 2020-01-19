package com.example.songily

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import android.util.Log


class AR2 : AppCompatActivity() {

    private lateinit var arFragment: ArFragment
    private var tapped: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar2)

        arFragment = supportFragmentManager.findFragmentById(R.id.arFragment) as ArFragment


        arFragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            var anchor: Anchor = hitResult.createAnchor()

            if (!tapped) {
                ModelRenderable.builder()
                    .setSource(this, Uri.parse("model.sfb"))
                    .build()
                    .thenAccept { md: ModelRenderable -> addModelToScene(anchor, md) }

                tapped = true
                Log.d("tap", "falsified")
            }
        }


    }


    private fun addModelToScene(anchor: Anchor, modelRenderable: ModelRenderable) {
        val anchorNode: AnchorNode = AnchorNode(anchor)

        //this is for transformable
        /*val transformableNode: TransformableNode = TransformableNode(arFragment.transformationSystem)
        transformableNode.setParent(anchorNode)
        transformableNode.setRenderable(modelRenderable)
        arFragment.arSceneView.scene.addChild(anchorNode)
        transformableNode.select()
         */


        val node: Node = Node()
        node.setParent(anchorNode)
        node.renderable = modelRenderable
        arFragment.arSceneView.scene.addChild(anchorNode)

        node.setOnTapListener { hitTestResult, motionEvent ->
            val alert = AlertDialog.Builder(this)
            alert.setTitle("You have Found a tune")
            alert.setMessage(
                "Guess now to get 6 notes" +
                        "\nCollect and guess later for 3 notes" +
                        "\nLeave to not interact with tune"
            )

            //*** List of current songs
            val currentList: MutableList<List<String>> = ArrayList()

            val current1: List<String> =
                applicationContext.assets.open("bob_dylan(like_a_rolling_stone).txt")
                    .bufferedReader().readLines()
            currentList.add(current1)

            val current2: List<String> =
                applicationContext.assets.open("david_bowie(life_on_mars_).txt")
                    .bufferedReader().readLines()
            currentList.add(current2)

            val current3: List<String> = applicationContext.assets.open("elton_john(your_song).txt")
                .bufferedReader().readLines()
            currentList.add(current3)

            val current4: List<String> =
                applicationContext.assets.open("guns_n_roses(sweet_child_o_mine).txt")
                    .bufferedReader().readLines()
            currentList.add(current4)

            val current5: List<String> = applicationContext.assets.open("john_lennon(imagine).txt")
                .bufferedReader().readLines()
            currentList.add(current5)

            val current6: List<String> =
                applicationContext.assets.open("judy_garland(over_the_rainbow).txt")
                    .bufferedReader().readLines()
            currentList.add(current6)

            val current7: List<String> =
                applicationContext.assets.open("led_zeppelin(stairway_to_heaven).txt")
                    .bufferedReader().readLines()
            currentList.add(current7)

            val current8: List<String> =
                applicationContext.assets.open("michael_jackson(billie_jean).txt")
                    .bufferedReader().readLines()
            currentList.add(current8)

            val current9: List<String> =
                applicationContext.assets.open("nirvana(smells_like_teen_spirit).txt")
                    .bufferedReader().readLines()
            currentList.add(current9)

            val current10: List<String> = applicationContext.assets.open("oasis(live_forever).txt")
                .bufferedReader().readLines()
            currentList.add(current10)

            val current11: List<String> =
                applicationContext.assets.open("queen(bohemian_rhapsody).txt")
                    .bufferedReader().readLines()
            currentList.add(current11)

            val current12: List<String> =
                applicationContext.assets.open("rolling_stones(I_can't_get_no_satisfaction).txt")
                    .bufferedReader().readLines()
            currentList.add(current12)

            val current13: List<String> =
                applicationContext.assets.open("sex_pistols(god_save_the_queen).txt")
                    .bufferedReader().readLines()
            currentList.add(current13)

            val current14: List<String> =
                applicationContext.assets.open("the_beatles(hey_jude).txt")
                    .bufferedReader().readLines()
            currentList.add(current14)

            val current15: List<String> =
                applicationContext.assets.open("the_clash(london_calling).txt")
                    .bufferedReader().readLines()
            currentList.add(current15)

            val current16: List<String> =
                applicationContext.assets.open("the_eagles(hotel_california).txt")
                    .bufferedReader().readLines()
            currentList.add(current16)

            val current17: List<String> =
                applicationContext.assets.open("the_kinks(waterloo_sunset).txt")
                    .bufferedReader().readLines()
            currentList.add(current17)

            val current18: List<String> = applicationContext.assets.open("u2(one).txt")
                .bufferedReader().readLines()
            currentList.add(current18)

            val current19: List<String> =
                applicationContext.assets.open("whitney_houston(i_will_always_love_you).txt")
                    .bufferedReader().readLines()
            currentList.add(current19)

            val curList = currentList[(0 until currentList.size - 1).random()]
            //*****

            alert.setPositiveButton("Guess", { dialogInterface: DialogInterface, i: Int ->
                val intent = Intent(this, GuessPage1::class.java)
                intent.putExtra("LYRIC", curList[((0 until curList.size - 1).random())])
                when {
                    curList.equals(current1) -> intent.putExtra("TITLE", "like a rolling stone")
                    curList.equals(current2) -> intent.putExtra("TITLE", "life on mars")
                    curList.equals(current3) -> intent.putExtra("TITLE", "your song")
                    curList.equals(current4) -> intent.putExtra("TITLE", "sweet child o mine")
                    curList.equals(current5) -> intent.putExtra("TITLE", "imagine")
                    curList.equals(current6) -> intent.putExtra("TITLE", "over the rainbow")
                    curList.equals(current7) -> intent.putExtra("TITLE", "stairway to heaven")
                    curList.equals(current8) -> intent.putExtra("TITLE", "billie jean")
                    curList.equals(current9) -> intent.putExtra("TITLE", "smells like teen spirit")
                    curList.equals(current10) -> intent.putExtra("TITLE", "live forever")
                    curList.equals(current11) -> intent.putExtra("TITLE", "bohemian rhapsody")
                    curList.equals(current12) -> intent.putExtra(
                        "TITLE",
                        "i can't get no satisfaction"
                    )
                    curList.equals(current13) -> intent.putExtra("TITLE", "god save the queen")
                    curList.equals(current14) -> intent.putExtra("TITLE", "hey jude")
                    curList.equals(current15) -> intent.putExtra("TITLE", "london calling")
                    curList.equals(current16) -> intent.putExtra("TITLE", "hotel california")
                    curList.equals(current17) -> intent.putExtra("TITLE", "waterloo sunset")
                    curList.equals(current18) -> intent.putExtra("TITLE", "one")
                    curList.equals(current19) -> intent.putExtra("TITLE", "i will always love you")
                }

                when {
                    curList.equals(current1) -> intent.putExtra("ARTIST", "bob dylan")
                    curList.equals(current2) -> intent.putExtra("ARTIST", "david bowie")
                    curList.equals(current3) -> intent.putExtra("ARTIST", "elton john")
                    curList.equals(current4) -> intent.putExtra("ARTIST", "guns n roses")
                    curList.equals(current5) -> intent.putExtra("ARTIST", "john lennon")
                    curList.equals(current6) -> intent.putExtra("ARTIST", "judy garland")
                    curList.equals(current7) -> intent.putExtra("ARTIST", "led zeppelin")
                    curList.equals(current8) -> intent.putExtra("ARTIST", "micheal jackson")
                    curList.equals(current9) -> intent.putExtra("ARTIST", "nirvana")
                    curList.equals(current10) -> intent.putExtra("ARTIST", "oasis")
                    curList.equals(current11) -> intent.putExtra("ARTIST", "queen")
                    curList.equals(current12) -> intent.putExtra("ARTIST", "rolling stones")
                    curList.equals(current13) -> intent.putExtra("ARTIST", "sex pistols")
                    curList.equals(current14) -> intent.putExtra("ARTIST", "the beatles")
                    curList.equals(current15) -> intent.putExtra("ARTIST", "the clash")
                    curList.equals(current16) -> intent.putExtra("ARTIST", "the eagles")
                    curList.equals(current17) -> intent.putExtra("ARTIST", "the kinks")
                    curList.equals(current18) -> intent.putExtra("ARTIST", "u2")
                    curList.equals(current19) -> intent.putExtra("ARTIST", "whitney houston")
                }
                startActivity(intent)

            })

            alert.setNeutralButton("Collect", { dialogInterface: DialogInterface, i: Int ->
                val intent = Intent(this, GuessCollectionAdder::class.java)
                intent.putExtra("LYRIC", curList[((0 until curList.size - 1).random())])

                when {
                    curList.equals(current1) -> intent.putExtra("TITLE", "like a rolling stone")
                    curList.equals(current2) -> intent.putExtra("TITLE", "life on mars")
                    curList.equals(current3) -> intent.putExtra("TITLE", "your song")
                    curList.equals(current4) -> intent.putExtra("TITLE", "sweet child o mine")
                    curList.equals(current5) -> intent.putExtra("TITLE", "imagine")
                    curList.equals(current6) -> intent.putExtra("TITLE", "over the rainbow")
                    curList.equals(current7) -> intent.putExtra("TITLE", "stairway to heaven")
                    curList.equals(current8) -> intent.putExtra("TITLE", "billie jean")
                    curList.equals(current9) -> intent.putExtra("TITLE", "smells like teen spirit")
                    curList.equals(current10) -> intent.putExtra("TITLE", "live forever")
                    curList.equals(current11) -> intent.putExtra("TITLE", "bohemian rhapsody")
                    curList.equals(current12) -> intent.putExtra(
                        "TITLE",
                        "i can't get no satisfaction"
                    )
                    curList.equals(current13) -> intent.putExtra("TITLE", "god save the queen")
                    curList.equals(current14) -> intent.putExtra("TITLE", "hey jude")
                    curList.equals(current15) -> intent.putExtra("TITLE", "london calling")
                    curList.equals(current16) -> intent.putExtra("TITLE", "hotel california")
                    curList.equals(current17) -> intent.putExtra("TITLE", "waterloo sunset")
                    curList.equals(current18) -> intent.putExtra("TITLE", "one")
                    curList.equals(current19) -> intent.putExtra("TITLE", "i will always love you")
                }

                when {
                    curList.equals(current1) -> intent.putExtra("ARTIST", "bob dylan")
                    curList.equals(current2) -> intent.putExtra("ARTIST", "david bowie")
                    curList.equals(current3) -> intent.putExtra("ARTIST", "elton john")
                    curList.equals(current4) -> intent.putExtra("ARTIST", "guns n roses")
                    curList.equals(current5) -> intent.putExtra("ARTIST", "john lennon")
                    curList.equals(current6) -> intent.putExtra("ARTIST", "judy garland")
                    curList.equals(current7) -> intent.putExtra("ARTIST", "led zeppelin")
                    curList.equals(current8) -> intent.putExtra("ARTIST", "micheal jackson")
                    curList.equals(current9) -> intent.putExtra("ARTIST", "nirvana")
                    curList.equals(current10) -> intent.putExtra("ARTIST", "oasis")
                    curList.equals(current11) -> intent.putExtra("ARTIST", "queen")
                    curList.equals(current12) -> intent.putExtra("ARTIST", "rolling stones")
                    curList.equals(current13) -> intent.putExtra("ARTIST", "sex pistols")
                    curList.equals(current14) -> intent.putExtra("ARTIST", "the beatles")
                    curList.equals(current15) -> intent.putExtra("ARTIST", "the clash")
                    curList.equals(current16) -> intent.putExtra("ARTIST", "the eagles")
                    curList.equals(current17) -> intent.putExtra("ARTIST", "the kinks")
                    curList.equals(current18) -> intent.putExtra("ARTIST", "u2")
                    curList.equals(current19) -> intent.putExtra("ARTIST", "whitney houston")
                }
                startActivity(intent)
            })

            alert.setNegativeButton("Leave", { dialogInterface: DialogInterface, i: Int -> })
            alert.show()
        }
    }
}
