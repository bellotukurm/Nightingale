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


class AR1 : AppCompatActivity() {

    private lateinit var arFragment: ArFragment
    private var tapped: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar1)

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
                applicationContext.assets.open("a_j_tracey(ladbroke_grove).txt")
                    .bufferedReader().readLines()
            currentList.add(current1)

            val current2: List<String> =
                applicationContext.assets.open("aitch(taste_make_it_shake).txt")
                    .bufferedReader().readLines()
            currentList.add(current2)

            val current3: List<String> =
                applicationContext.assets.open("ariana_grande___miley_cyrus___lana_del_rey(don't_call_me_angle).txt")
                    .bufferedReader().readLines()
            currentList.add(current3)

            val current4: List<String> = applicationContext.assets.open("dave(professor_x).txt")
                .bufferedReader().readLines()
            currentList.add(current4)

            val current5: List<String> =
                applicationContext.assets.open("dermot_kennedy(outnumbered).txt")
                    .bufferedReader().readLines()
            currentList.add(current5)

            val current6: List<String> =
                applicationContext.assets.open("dominic_fike(3_nights).txt")
                    .bufferedReader().readLines()
            currentList.add(current6)

            val current7: List<String> =
                applicationContext.assets.open("ed_sheeran_ft_stormzy(take_me_back_to_london).txt")
                    .bufferedReader().readLines()
            currentList.add(current7)

            val current8: List<String> = applicationContext.assets.open("headie_one(both).txt")
                .bufferedReader().readLines()
            currentList.add(current8)

            val current9: List<String> = applicationContext.assets.open("joel_corry(sorry).txt")
                .bufferedReader().readLines()
            currentList.add(current9)

            val current10: List<String> =
                applicationContext.assets.open("jorja_smith_ft_burna_boy(be_honest).txt")
                    .bufferedReader().readLines()
            currentList.add(current10)

            val current11: List<String> =
                applicationContext.assets.open("kygo_&_whitney_houston(higher_love).txt")
                    .bufferedReader().readLines()
            currentList.add(current11)

            val current12: List<String> = applicationContext.assets.open("lil_tecca(ransom).txt")
                .bufferedReader().readLines()
            currentList.add(current12)

            val current13: List<String> = applicationContext.assets.open("post_malone(circles).txt")
                .bufferedReader().readLines()
            currentList.add(current13)

            val current14: List<String> =
                applicationContext.assets.open("post_malone_ft_young_thug(goodbyes).txt")
                    .bufferedReader().readLines()
            currentList.add(current14)

            val current15: List<String> = applicationContext.assets.open("regard(ride_it).txt")
                .bufferedReader().readLines()
            currentList.add(current15)

            val current16: List<String> =
                applicationContext.assets.open("sam_feldt_ft_rani(post_malone).txt")
                    .bufferedReader().readLines()
            currentList.add(current16)

            val current17: List<String> =
                applicationContext.assets.open("sam_smith(how_do_you_sleep).txt")
                    .bufferedReader().readLines()
            currentList.add(current17)

            val current18: List<String> =
                applicationContext.assets.open("shawn_mendes___camila_cabello(senorita).txt")
                    .bufferedReader().readLines()
            currentList.add(current18)

            val current19: List<String> =
                applicationContext.assets.open("tones_&_i(dance_monkey).txt")
                    .bufferedReader().readLines()
            currentList.add(current19)

            val current20: List<String> =
                applicationContext.assets.open("young_t_&_bugsey_ft_aitch(strike_a_pose).txt")
                    .bufferedReader().readLines()
            currentList.add(current20)

            val curList = currentList[(0 until currentList.size - 1).random()]
            //*****

            alert.setPositiveButton("Guess", { dialogInterface: DialogInterface, i: Int ->

                val intent = Intent(this, GuessPage1::class.java)
                intent.putExtra("LYRIC", curList[((0 until curList.size - 1).random())])

                when {
                    curList.equals(current1) -> intent.putExtra("TITLE", "ladbroke grove")
                    curList.equals(current2) -> intent.putExtra("TITLE", "taste make it shake")
                    curList.equals(current3) -> intent.putExtra("TITLE", "don't call me angel")
                    curList.equals(current4) -> intent.putExtra("TITLE", "professor x")
                    curList.equals(current5) -> intent.putExtra("TITLE", "outnumbered")
                    curList.equals(current6) -> intent.putExtra("TITLE", "3 nights")
                    curList.equals(current7) -> intent.putExtra("TITLE", "take me back to london")
                    curList.equals(current8) -> intent.putExtra("TITLE", "both")
                    curList.equals(current9) -> intent.putExtra("TITLE", "sorry")
                    curList.equals(current10) -> intent.putExtra("TITLE", "be honest")
                    curList.equals(current11) -> intent.putExtra("TITLE", "higher love")
                    curList.equals(current12) -> intent.putExtra("TITLE", "ransom")
                    curList.equals(current13) -> intent.putExtra("TITLE", "circles")
                    curList.equals(current14) -> intent.putExtra("TITLE", "goodbyes")
                    curList.equals(current15) -> intent.putExtra("TITLE", "ride it")
                    curList.equals(current16) -> intent.putExtra("TITLE", "post malone")
                    curList.equals(current17) -> intent.putExtra("TITLE", "how do you sleep")
                    curList.equals(current18) -> intent.putExtra("TITLE", "senorita")
                    curList.equals(current19) -> intent.putExtra("TITLE", "dance monkey")
                    curList.equals(current20) -> intent.putExtra("TITLE", "strike a pose")
                }

                when {
                    curList.equals(current1) -> intent.putExtra("ARTIST", "aj tracey")
                    curList.equals(current2) -> intent.putExtra("ARTIST", "aitch")
                    curList.equals(current3) -> intent.putExtra(
                        "ARTIST",
                        "ariana grande, miley cyrus, lana del rey"
                    )
                    curList.equals(current4) -> intent.putExtra("ARTIST", "dave")
                    curList.equals(current5) -> intent.putExtra("ARTIST", "dermot kennedy")
                    curList.equals(current6) -> intent.putExtra("ARTIST", "dominic fike")
                    curList.equals(current7) -> intent.putExtra("ARTIST", "ed sheeran ft stormzy")
                    curList.equals(current8) -> intent.putExtra("ARTIST", "headie one")
                    curList.equals(current9) -> intent.putExtra("ARTIST", "joel corry")
                    curList.equals(current10) -> intent.putExtra("ARTIST", "jorja smith")
                    curList.equals(current11) -> intent.putExtra("ARTIST", "kygo, whitney houston")
                    curList.equals(current12) -> intent.putExtra("ARTIST", "lil tecca")
                    curList.equals(current13) -> intent.putExtra("ARTIST", "post malone")
                    curList.equals(current14) -> intent.putExtra(
                        "ARTIST",
                        "post malone ft young thug"
                    )
                    curList.equals(current15) -> intent.putExtra("ARTIST", "regard")
                    curList.equals(current16) -> intent.putExtra("ARTIST", "sam feldt ft rani")
                    curList.equals(current17) -> intent.putExtra("ARTIST", "sam smith")
                    curList.equals(current18) -> intent.putExtra(
                        "ARTIST",
                        "shawn mendes, camila cabello"
                    )
                    curList.equals(current19) -> intent.putExtra("ARTIST", "tones and i")
                    curList.equals(current20) -> intent.putExtra(
                        "ARTIST",
                        "young t, bugsey ft aitch"
                    )
                }

                startActivity(intent)

            })

            alert.setNeutralButton("Collect", { dialogInterface: DialogInterface, i: Int ->
                val intent = Intent(this, GuessCollectionAdder::class.java)
                intent.putExtra("LYRIC", curList[((0 until curList.size - 1).random())])

                when {
                    curList.equals(current1) -> intent.putExtra("TITLE", "ladbroke grove")
                    curList.equals(current2) -> intent.putExtra("TITLE", "taste make it shake")
                    curList.equals(current3) -> intent.putExtra("TITLE", "don't call me angel")
                    curList.equals(current4) -> intent.putExtra("TITLE", "professor x")
                    curList.equals(current5) -> intent.putExtra("TITLE", "outnumbered")
                    curList.equals(current6) -> intent.putExtra("TITLE", "3 nights")
                    curList.equals(current7) -> intent.putExtra("TITLE", "take me back to london")
                    curList.equals(current8) -> intent.putExtra("TITLE", "both")
                    curList.equals(current9) -> intent.putExtra("TITLE", "sorry")
                    curList.equals(current10) -> intent.putExtra("TITLE", "be honest")
                    curList.equals(current11) -> intent.putExtra("TITLE", "higher love")
                    curList.equals(current12) -> intent.putExtra("TITLE", "ransom")
                    curList.equals(current13) -> intent.putExtra("TITLE", "circles")
                    curList.equals(current14) -> intent.putExtra("TITLE", "goodbyes")
                    curList.equals(current15) -> intent.putExtra("TITLE", "ride it")
                    curList.equals(current16) -> intent.putExtra("TITLE", "post malone")
                    curList.equals(current17) -> intent.putExtra("TITLE", "how do you sleep")
                    curList.equals(current18) -> intent.putExtra("TITLE", "senorita")
                    curList.equals(current19) -> intent.putExtra("TITLE", "dance monkey")
                    curList.equals(current20) -> intent.putExtra("TITLE", "strike a pose")
                }

                when {
                    curList.equals(current1) -> intent.putExtra("ARTIST", "aj tracey")
                    curList.equals(current2) -> intent.putExtra("ARTIST", "aitch")
                    curList.equals(current3) -> intent.putExtra(
                        "ARTIST",
                        "ariana grande, miley cyrus, lana del rey"
                    )
                    curList.equals(current4) -> intent.putExtra("ARTIST", "dave")
                    curList.equals(current5) -> intent.putExtra("ARTIST", "dermot kennedy")
                    curList.equals(current6) -> intent.putExtra("ARTIST", "dominic fike")
                    curList.equals(current7) -> intent.putExtra("ARTIST", "ed sheeran ft stormzy")
                    curList.equals(current8) -> intent.putExtra("ARTIST", "headie one")
                    curList.equals(current9) -> intent.putExtra("ARTIST", "joel corry")
                    curList.equals(current10) -> intent.putExtra("ARTIST", "jorja smith")
                    curList.equals(current11) -> intent.putExtra("ARTIST", "kygo, whitney houston")
                    curList.equals(current12) -> intent.putExtra("ARTIST", "lil tecca")
                    curList.equals(current13) -> intent.putExtra("ARTIST", "post malone")
                    curList.equals(current14) -> intent.putExtra(
                        "ARTIST",
                        "post malone ft young thug"
                    )
                    curList.equals(current15) -> intent.putExtra("ARTIST", "regard")
                    curList.equals(current16) -> intent.putExtra("ARTIST", "sam feldt ft rani")
                    curList.equals(current17) -> intent.putExtra("ARTIST", "sam smith")
                    curList.equals(current18) -> intent.putExtra(
                        "ARTIST",
                        "shawn mendes, camila cabello"
                    )
                    curList.equals(current19) -> intent.putExtra("ARTIST", "tones and i")
                    curList.equals(current20) -> intent.putExtra(
                        "ARTIST",
                        "young t, bugsey ft aitch"
                    )
                }
                startActivity(intent)
            })

            alert.setNegativeButton("Leave", { dialogInterface: DialogInterface, i: Int -> })
            alert.show()
        }
    }
}
